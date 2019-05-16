package ru.levelup.yulia.zaykova.qa.homework_6.task_1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.entities.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected WebDriver driver;
    private ChromeOptions options;
    private Properties properties;

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        WebDriverManager.chromedriver().setup();
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src/test/resources/selenium/data.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        options = new ChromeOptions();
        options.addArguments("--lang=en");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("browser.implicity.timeout.ms")), TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(properties.getProperty("browser.page.load.timeout.ms")), TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(Long.parseLong(properties.getProperty("browser.script.load.timeout.ms")), TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        driver.get(properties.getProperty("url"));
        initPages();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        driver.quit();
    }

    protected User getAdmin() {
        return User.getBuilder().setUsername(getAdminUsername()).setPassword(getAdminPassword()).build();
    }

    protected String getAdminUsername() {
        return properties.getProperty("admin.username");
    }

    protected String getAdminPassword() {
        return properties.getProperty("admin.password");
    }

    protected abstract void initPages();
}
