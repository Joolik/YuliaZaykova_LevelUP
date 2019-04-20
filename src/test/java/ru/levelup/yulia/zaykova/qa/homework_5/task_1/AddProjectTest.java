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

        // Maximize window
        // TODO Это настройка, которую лучше выносить в Before
        driver.manage().window().maximize();

        // Open test site by URL
        // TODO Если не предполагается возможности использования других страниц для отнрытия, то тоже лучше в вынести в before
        driver.get("http://khda91.fvds.ru/mantisbt/");
    }

    @Test
    public void addProjectTest() {

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
        assertThat(driver.getTitle(), equalTo("Manage - MantisBT"));

        // Click Manage Projects
        // TODO Можно было использовать By.partialLinkText || By.linkText
        driver.findElement(By.linkText("Manage Projects")).click();

        // Check "Create new project" button
        WebElement btnNewProject = driver.findElement(By.xpath("//button[text()='Create New Project']"));
        assertThat(btnNewProject, notNullValue());

        // Click "Create new project" button
        btnNewProject.click();

        // Check fields on the "Add project" view

        // TODO Map<String, String> был бы лучше чем List<String[]>
        // TODO Можно использовать By.id как параметер
        Map<String, String> expectedFields = new LinkedHashMap<>();
        expectedFields.put("* Project Name", "project-name");
        expectedFields.put("Status", "project-status");
        expectedFields.put("Inherit Global Categories", "project-inherit-global");
        expectedFields.put("View Status", "project-view-state");
        expectedFields.put("Description", "project-description");

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

        // Fill project information
        String projectName = RandomStringUtils.randomAlphabetic(1) + "ProjYZ" + RandomStringUtils.randomNumeric(6);
        String status = "development";
        String viewStatus = "public";
        String description = "Test description for " + projectName + " !";

        // Fill Project name
        // TODO By.id
        driver.findElement(By.id("project-name")).sendKeys(projectName);

        // Select Status
        // TODO By.id
        Select selectStatus = new Select(driver.findElement(By.id("project-status")));
        selectStatus.selectByVisibleText(status);

        // Checkbox
        // TODO By.id
        // TODO 2Dmitry: С By.id ошибка. Не дает кликнуть по <input>, только по <span> или <label>
        // TODO " WebDriverException: unknown error: Element <input type="checkbox" class="ace" id="project-inherit-global" name="inherit_global" checked="checked"> is not clickable at point (408, 282). Other element would receive the click: <span class="lbl"></span>

        if (driver.findElement(By.id("project-inherit-global")).isSelected()) {
            driver.findElement(By.xpath("//input[@id='project-inherit-global']/following-sibling::span")).click();
            //driver.findElement(By.id("project-inherit-global")).click();
        }

        // Select View Status
        Select selectViewStatus = new Select(driver.findElement(By.id("project-view-state")));
        selectViewStatus.selectByVisibleText(viewStatus);

        // Fill Description
        // TODO By.id
        driver.findElement(By.id("project-description")).sendKeys(description);

        // Click "Add project" button
        driver.findElement(By.xpath("//input[@type='submit' and contains(@value,'Add Project')]")).click();

        // Check added project info
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        wait.until(ExpectedConditions.titleContains("Manage Projects - MantisBT"));

        // Map (key: column name, value: index)
        List<WebElement> listHead = driver.findElements(By.xpath("//div[contains(@class,'col-md-12' )]/div[contains(@class, 'widget-box')]//table//thead//a"));
        Map<String, Integer> mapHead = new HashMap<>();
        int indexOfColumn = 1;
        for (WebElement we : listHead) {
            mapHead.put(we.getText().trim(), indexOfColumn);
            indexOfColumn++;
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

