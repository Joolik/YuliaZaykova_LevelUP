package ru.levelup.yulia.zaykova.qa.homework_6.task_2;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.*;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.entities.User;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.enums.LeftSideMenuItems;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FilterIssuesTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private ViewIssuesPage viewIssuesPage;

//    @Override
//    @BeforeMethod(alwaysRun = true)
//    public void setUp() {
//        super.setUp();
        // TODO Аналогичо как в классе AddUserTest
//        loginPage = new LoginPage(driver);
//        homePage = new HomePage(driver);
//        viewIssuesPage = new ViewIssuesPage(driver);
//    }

    protected void initPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        viewIssuesPage = new ViewIssuesPage(driver);
    }

    @Test
    public void filterIssuesTest() {

        // Assert browser title
        assertThat(loginPage.getPageTitle(), equalTo(LoginPage.PAGE_TITLE));

        // Login as admin/admin
        loginPage.login(getAdmin());

        // Assert username in the right-top side of screen
        assertThat(homePage.getCurrUserName(), equalToIgnoringCase(getAdminUsername()));

        // Assert left side menu
        assertThat(homePage.getLeftSideMenu(), not(empty()));

        // Click "View Issues" button at the left side menu
        homePage.selectLeftSideMenuItem(LeftSideMenuItems.VIEW_ISSUES);
        assertThat(viewIssuesPage.getPageTitle(), equalTo(ViewIssuesPage.PAGE_TITLE));

        // Reset filter
        viewIssuesPage.filterReset();

        // Set filter values
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src/test/resources/selenium/FilterIssue.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String priority = properties.getProperty("priority");
        String severity = properties.getProperty("severity");
        String status = properties.getProperty("status");
        String startDateSubmitted = properties.getProperty("startDateSubmitted");
        String endDateSubmitted = properties.getProperty("endDateSubmitted");

        viewIssuesPage.setPriority(priority);
        viewIssuesPage.setSeverity(severity);
        viewIssuesPage.setStatus(status);
        viewIssuesPage.setFilterByDateSubmitted(startDateSubmitted, endDateSubmitted);

        // Click Apply Filter
        viewIssuesPage.submitApplyFilter();

        // Check results
        assertThat("No records for this filter", viewIssuesPage.getFilteredBugList().size(), greaterThan(0));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        // Reset filter
        viewIssuesPage.filterReset();

        // Logout
        viewIssuesPage.logout();

        super.tearDown();
    }
}
