package ru.levelup.yulia.zaykova.qa.homework_5.task_1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddUserTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        driver = new ChromeDriver(options);
    }

    @Test
    public void addProjectTest() {

        // Maximize window
        // TODO Это настройка, которую лучше выносить в Before
        driver.manage().window().maximize();

        // Open test site by URL
        // TODO Если не предполагается возможности использования других страниц для отнрытия, то тоже лучше в вынести в before
        driver.get("http://khda91.fvds.ru/mantisbt/");

        // Assert browser title
        assertThat(driver.getTitle(), equalTo("MantisBT"));

        // Login as admin/admin
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.cssSelector(".btn-success")).click();
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.cssSelector(".btn-success")).click();

        // Assert username in the right-top side of screen
        assertThat(driver.findElement(By.cssSelector(".user-info")).getText(), equalToIgnoringCase("admin"));

        // Assert left side menu
        List<WebElement> menuLeftSide = driver.findElements(By.xpath("//div[@id='sidebar']//a"));
        assertThat(menuLeftSide, not(empty()));

        // Click "Manage" button at the left side menu
        for (WebElement we : menuLeftSide) {
            if (we.getText().trim().equals("Manage")) {
                we.click();
                break;
            }
        }

        // Click "Manage Users" button at the top menu on the "Manage MantisBT" page
        // TODO Можно было использовать By.partialLinkText || By.linkText
        driver.findElement(By.xpath("//ul[contains(@class,'nav-tabs')]//a[text()='Manage Users']")).click();

        // Check "Create New Account" button
        WebElement btnNewProject = driver.findElement(By.xpath("//a[text()='Create New Account']"));

        // Click "Create New Account" button
        btnNewProject.click();

        // Check fields on the "Create New Account" view

        // TODO Map<String, String> был бы лучше чем List<String[]>
        List<String[]> expectedFields = new ArrayList<>();
        // TODO Можно использовать By.id как параметер
        expectedFields.add(new String[]{"Username", "//input[@id='user-username']"});
        expectedFields.add(new String[]{"Real Name", "//input[@id='user-realname']"});
        expectedFields.add(new String[]{"E-mail", "//input[@id='email-field']"});
        expectedFields.add(new String[]{"Password", "//input[@id='user-password']"});
        expectedFields.add(new String[]{"Verify Password", "//input[@id='user-verify-password']"});
        expectedFields.add(new String[]{"Access Level", "//select[@id='user-access-level']"});
        expectedFields.add(new String[]{"Enabled", "//input[@id='user-enabled']"});
        expectedFields.add(new String[]{"Protected", "//input[@id='user-protected']"});


        List<WebElement> actualWE = driver.findElements(By.xpath("//div[@class='widget-body']//table//tr"));
        assertThat("Count of expected fields not equal to count of actual fields",
                expectedFields.size(), equalTo(actualWE.size()));

        SoftAssert softAssert = new SoftAssert();
        int i = 0;
        for (String[] key : expectedFields) {
            softAssert.assertEquals(actualWE.get(i).findElement(By.xpath("./td[1]")).getText().trim(), key[0]);
            // TODO actualWE.get(i).findElements(key[1])
            softAssert.assertEquals(actualWE.get(i).findElements(By.xpath("./td[2]" + key[1])).size(), 1,
                    "Number of elememts " + key[1] + " =" + actualWE.get(i).findElements(By.xpath("./td[2]" + key[1])).size());
            i++;
        }
        softAssert.assertAll();

        // Fill user inforamtion
        String username = "UserYZ" + RandomStringUtils.randomNumeric(6);
        String realname = "Bobby Wong";
        String email = username + "@gmail.com";
        String password = "yuGFGH7384dsf";
        String verifyPassword = password;
        String accessLevel = "reporter";

        // TODO By.id
        driver.findElement(By.xpath("//input[@id='user-username']")).sendKeys(username);
        // TODO By.id
        driver.findElement(By.xpath("//input[@id='user-realname']")).sendKeys(realname);
        // TODO By.id
        driver.findElement(By.xpath("//input[@id='email-field']")).sendKeys(email);
        // TODO By.id
        driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys(password);
        // TODO By.id
        driver.findElement(By.xpath("//input[@id='user-verify-password']")).sendKeys(verifyPassword);
        // TODO By.id
        Select selectStatus = new Select(driver.findElement(By.xpath("//select[@id='user-access-level']")));
        selectStatus.selectByVisibleText(accessLevel);
        // TODO By.id
        if (!driver.findElement(By.xpath("//input[@id='user-enabled']")).isSelected()) {
            driver.findElement(By.xpath("//input[@id='user-enabled']/parent::label")).click();
        }
        // TODO By.id
        if (driver.findElement(By.xpath("//input[@id='user-protected']")).isSelected()) {
            driver.findElement(By.xpath("//input[@id='user-protected']/parent::label")).click();
        }

        // Click "Create User" button
        driver.findElement(By.xpath("//input[@type='submit' and contains(@value,'Create User')]")).click();

        // Check added user info
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Update User']")));

        // TODO By.partialLinkText || By.linkText
        driver.findElement(By.xpath("//ul[contains(@class,'nav-tabs')]//a[text()='Manage Users']")).click();

        // Map (key: column name, value: index)
        List<WebElement> listHead = driver.findElements(By.xpath("//table//thead//a"));
        Map<String, Integer> mapHead = new HashMap<>();
        // TODO что значит k
        int k = 1;
        for (WebElement we : listHead) {
            mapHead.put(we.getText().trim(), k);
            k++;
        }

        // List of projects
        List<WebElement> projectsList = driver.findElements(By.xpath("//div[contains(@class,'col-md-12' )]/div[contains(@class, 'widget-box')]//table//tbody/tr"));

        // Search project with Name = projectName
        WebElement userInfo = null;
        for (WebElement we : projectsList) {
            if (we.findElement(By.xpath("./td[" + mapHead.get("Username") + "]")).getText().equals(username)) {
                userInfo = we;
                // TODO break???
            }
        }

        assertThat("User with name " + userInfo + " not found", userInfo, notNullValue());

        softAssert.assertEquals(userInfo.findElement(By.xpath("./td[" + mapHead.get("Real Name") + "]")).getText(), realname);
        softAssert.assertEquals(userInfo.findElement(By.xpath("./td[" + mapHead.get("E-mail") + "]")).getText(), email);
        softAssert.assertEquals(userInfo.findElement(By.xpath("./td[" + mapHead.get("Access Level") + "]")).getText(), accessLevel);
        softAssert.assertNotNull(userInfo.findElements(By.xpath("./td[" + mapHead.get("Enabled") + "]/i")));
        softAssert.assertEquals(userInfo.findElements(By.xpath("./td[" + mapHead.get("Protected") + "]/i")).size(), 0);

        softAssert.assertAll();

        // Logout
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Logout')]")).click();

        // Login under created user
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.cssSelector(".btn-success")).click();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector(".btn-success")).click();

        // Assert User name in the right-top side of screen that user is loggined
        assertThat(driver.findElement(By.cssSelector(".user-info")).getText(), equalToIgnoringCase(username));

        // TODO Не используйте пожалуйста Thread.sleep
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Logout
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Logout')]")).click();

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Close browser
        driver.close();
        driver.quit();
    }

}
