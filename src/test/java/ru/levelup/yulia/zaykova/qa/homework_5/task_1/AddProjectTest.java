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

public class AddProjectTest {

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

        // Assert left-side menu
        List<WebElement> menuLeftSide = driver.findElements(By.xpath("//div[@id='sidebar']//a"));
        assertThat(menuLeftSide, not(empty()));

        // Click Manage
        for (WebElement we : menuLeftSide) {
            if (we.getText().trim().equals("Manage")) {
                we.click();
                break;
            }
        }

        // Click Manage Projects
        // TODO Можно было использовать By.partialLinkText || By.linkText
        driver.findElement(By.xpath("//ul[contains(@class,'nav-tabs')]//a[text()='Manage Projects']")).click();

        // Check "Create new project" button
        WebElement btnNewProject = driver.findElement(By.xpath("//button[text()='Create New Project']"));
        assertThat(btnNewProject, notNullValue());

        // Click "Create new project" button
        btnNewProject.click();

        // Check fields on the "Add project" view

        // TODO Map<String, String> был бы лучше чем List<String[]>
        List<String[]> expectedFields = new ArrayList<>();
        // TODO Можно использовать By.id как параметер
        expectedFields.add(new String[]{"* Project Name", "//input[@id='project-name']"});
        expectedFields.add(new String[]{"Status", "//select[@id='project-status']"});
        expectedFields.add(new String[]{"Inherit Global Categories", "//input[@id='project-inherit-global']"});
        expectedFields.add(new String[]{"View Status", "//select[@id='project-view-state']"});
        expectedFields.add(new String[]{"Description", "//textarea[@id='project-description']"});

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

        // Fill project information
        String projectName = RandomStringUtils.randomAlphabetic(1) + "ProjYZ" + RandomStringUtils.randomNumeric(6);
        String status = "development";
        String viewStatus = "public";
        String description = "Test description for " + projectName + "!";

        // Fill Project name
        // TODO By.id
        driver.findElement(By.xpath("//input[@id='project-name']")).sendKeys(projectName);

        // Select Status
        // TODO By.id
        Select selectStatus = new Select(driver.findElement(By.xpath("//select[@id='project-status']")));
        selectStatus.selectByVisibleText(status);

        // Checkbox
        // TODO By.id
        if (driver.findElement(By.xpath("//input[@id='project-inherit-global']")).isSelected()) {
            driver.findElement(By.xpath("//input[@id='project-inherit-global']/parent::label")).click();
        }

        // Select View Status
        Select selectViewStatus = new Select(driver.findElement(By.xpath("//select[@id='project-view-state']")));
        selectViewStatus.selectByVisibleText(viewStatus);

        // Fill Description
        // TODO By.id
        driver.findElement(By.xpath("//textarea[@id='project-description']")).sendKeys(description);

        // Click "Add project" button
        driver.findElement(By.xpath("//input[@type='submit' and contains(@value,'Add Project')]")).click();

        // Check added project info
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        wait.until(ExpectedConditions.titleContains("Manage Projects - MantisBT"));

        // Map (key: column name, value: index)
        List<WebElement> listHead = driver.findElements(By.xpath("//div[contains(@class,'col-md-12' )]/div[contains(@class, 'widget-box')]//table//thead//a"));
        Map<String, Integer> mapHead = new HashMap<>();
        int k = 1;
        for (WebElement we : listHead) {
            mapHead.put(we.getText().trim(), k);
            k++;
        }

        // List of projects
        List<WebElement> projectsList = driver.findElements(By.xpath("//div[contains(@class,'col-md-12' )]/div[contains(@class, 'widget-box')]//table//tbody/tr"));

        // Search project with Name = projectName
        WebElement projectInfo = null;
        for (WebElement we : projectsList) {
            if (we.findElement(By.xpath("./td[" + mapHead.get("Name") + "]")).getText().equals(projectName)) {
                projectInfo = we;
            }
        }

        assertThat("Project with name " + projectName + " not found", projectInfo, notNullValue());

        softAssert.assertEquals(projectInfo.findElement(By.xpath("./td[" + mapHead.get("Status") + "]")).getText(), status);
        softAssert.assertEquals(projectInfo.findElement(By.xpath("./td[" + mapHead.get("View Status") + "]")).getText(), viewStatus);
        softAssert.assertEquals(projectInfo.findElement(By.xpath("./td[" + mapHead.get("Description") + "]")).getText(), description);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        // Logout
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Logout')]")).click();

        // Close browser
        driver.close();
        driver.quit();
    }
}

