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

        // Maximize window
        // TODO Это настройка, которую лучше выносить в Before
        driver.manage().window().maximize();

        // Open test site by URL
        // TODO Если не предполагается возможности использования других страниц для отнрытия, то тоже лучше в вынести в before
        driver.get("http://khda91.fvds.ru/mantisbt/");
    }

    @Test
    public void addUserTest() {

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
        assertThat(driver.getTitle(), equalTo("Manage - MantisBT"));

        // Click "Manage Users" button at the top menu on the "Manage MantisBT" page
        // TODO Можно было использовать By.partialLinkText || By.linkText
        driver.findElement(By.partialLinkText("Manage Users")).click();
        assertThat(driver.getTitle(), equalTo("Manage Users - MantisBT"));

        // Check "Create New Account" button
        WebElement btnNewAccount = driver.findElement(By.xpath("//a[text()='Create New Account']"));

        // Click "Create New Account" button
        btnNewAccount.click();

        // Check fields on the "Create New Account" view

        // TODO Map<String, String> был бы лучше чем List<String[]>
        Map<String, String> expectedFields = new LinkedHashMap<>();
        // TODO Можно использовать By.id как параметер
        expectedFields.put("Username", "user-username");
        expectedFields.put("Real Name", "user-realname");
        expectedFields.put("E-mail", "email-field");
        expectedFields.put("Password", "user-password");
        expectedFields.put("Verify Password", "user-verify-password");
        expectedFields.put("Access Level", "user-access-level");
        expectedFields.put("Enabled", "user-enabled");
        expectedFields.put("Protected", "user-protected");

        List<WebElement> actualFields = driver.findElements(By.xpath("//div[@class='widget-body']//table//tr"));

        assertThat("Count of expected fields not equal to count of actual fields",
                expectedFields.size(), equalTo(actualFields.size()));

        SoftAssert softAssert = new SoftAssert();

        Set<String> expectedFieldNames = expectedFields.keySet();
        int i = 0;
        for (String expectedFieldName : expectedFieldNames) {
            softAssert.assertEquals(actualFields.get(i).findElement(By.xpath("./td[1]")).getText().trim(), expectedFieldName);
            // TODO actualWE.get(i).findElements(key[1])
            softAssert.assertEquals(actualFields.get(i).findElements(By.id(expectedFields.get(expectedFieldName))).size(), 1,
                    "Field \"" + expectedFieldName + "\": wrong number of elememts id=\"" + expectedFields.get(expectedFieldName) + "\":");

            i++;
        }

        softAssert.assertAll();

        // Fill user inforamtion
        String username = "UserYZ" + RandomStringUtils.randomNumeric(6);
        String realname = "Bobby Wong";
        String email = username + "@qrail.com";
        String password = "yuGFGH7384dsf";
        String verifyPassword = password;
        String accessLevel = "reporter";

        // TODO By.id
        driver.findElement(By.id("user-username")).sendKeys(username);
        // TODO By.id
        driver.findElement(By.id("user-realname")).sendKeys(realname);
        // TODO By.id
        driver.findElement(By.id("email-field")).sendKeys(email);
        // TODO By.id
        driver.findElement(By.id("user-password")).sendKeys(password);
        // TODO By.id
        driver.findElement(By.id("user-verify-password")).sendKeys(verifyPassword);
        // TODO By.id
        Select selectStatus = new Select(driver.findElement(By.id("user-access-level")));
        selectStatus.selectByVisibleText(accessLevel);
        // TODO By.id
        // TODO 2Dmitry: Ошибка "unknown error: Element <input type="checkbox" class="ace" id="user-enabled" name="enabled" checked="checked"> is not clickable at point (470, 452). Other element would receive the click: <span class="lbl"></span>
        if (!driver.findElement(By.id("user-enabled")).isSelected()) {
            driver.findElement(By.xpath("//input[@id='user-enabled']/parent::label")).click();
        }
        //if (driver.findElement(By.id("user-enabled")).isSelected()) {
        //    driver.findElement(By.id("user-enabled")).click();
        //}
        // TODO By.id
        // TODO 2Dmitry: Ошибка "unknown error: Element <input type="checkbox" class="ace" id="user-protected" name="protected"> is not clickable at point (470, 484). Other element would receive the click: <span class="lbl"></span>
        if (driver.findElement(By.id("user-protected")).isSelected()) {
            driver.findElement(By.xpath("//input[@id='user-protected']/parent::label")).click();
        }
        //if (!driver.findElement(By.id("user-protected")).isSelected()) {
        //    driver.findElement(By.id("user-protected")).click();
        //}

        // Click "Create User" button
        driver.findElement(By.xpath("//input[@type='submit' and contains(@value,'Create User')]")).click();

        // Check added user info
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Manage Users")));

        // TODO By.partialLinkText || By.linkText
        driver.findElement(By.partialLinkText("Manage Users")).click();

        // Get index of column by column name
        List<WebElement> listHead = driver.findElements(By.xpath("//div[contains(@class,'col-md-12' )]/div[contains(@class, 'widget-box')]//table//thead//a"));
        Map<String, Integer> tableHead = new HashMap<>();
        // TODO что значит k
        int indexOfColumn = 1;
        for (WebElement we : listHead) {
            tableHead.put(we.getText().trim(), indexOfColumn);
            indexOfColumn++;
        }

        // List of users
        List<WebElement> usersList = driver.findElements(By.xpath("//div[contains(@class,'col-md-12' )]/div[contains(@class, 'widget-box')]//table//tbody/tr"));

        // Search user with name = username
        WebElement userInfo = null;
        for (WebElement we : usersList) {
            if (we.findElement(By.xpath("./td[" + tableHead.get("Username") + "]")).getText().equals(username)) {
                userInfo = we;
                // TODO break???
                break;
            }
        }

        assertThat("User with name " + username + " not found", userInfo, notNullValue());

        softAssert.assertEquals(userInfo.findElement(By.xpath("./td[" + tableHead.get("Real Name") + "]")).getText(), realname);
        softAssert.assertEquals(userInfo.findElement(By.xpath("./td[" + tableHead.get("E-mail") + "]")).getText(), email);
        softAssert.assertEquals(userInfo.findElement(By.xpath("./td[" + tableHead.get("Access Level") + "]")).getText(), accessLevel);
        softAssert.assertNotNull(userInfo.findElements(By.xpath("./td[" + tableHead.get("Enabled") + "]/i")));
        softAssert.assertEquals(userInfo.findElements(By.xpath("./td[" + tableHead.get("Protected") + "]/i")).size(), 0);

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
