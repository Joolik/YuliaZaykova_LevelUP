package ru.levelup.yulia.zaykova.qa.homework_6.task_1;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.enums.LeftSideMenuItems;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.enums.ManageTabItems;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddUserTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private ManagePage managePage;
    private ManageUsersPage manageUsersPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        managePage = new ManagePage(driver);
        manageUsersPage = new ManageUsersPage(driver);
    }

    @Test
    public void addUserTest() {

        // Assert browser title
        assertThat(loginPage.getPageTitle(), equalTo(LoginPage.PAGE_TITLE));

        // Login as admin/admin
        loginPage.login(getAdminUsername(), getAdminPassword());

        // Assert username in the right-top side of screen
        assertThat(homePage.getCurrUserName(), equalToIgnoringCase(getAdminUsername()));

        // Assert left side menu
        assertThat(homePage.getLeftSideMenu(), not(empty()));

        // Click "Manage" button at the left side menu
        homePage.selectLeftSideMenuItem(LeftSideMenuItems.MANAGE);
        assertThat(managePage.getPageTitle(), equalTo(ManagePage.PAGE_TITLE));

        // Click "Manage Users" button at the top menu on the "Manage MantisBT" page
        managePage.selectMenuTabsItem(ManageTabItems.MANAGE_USERS);
        assertThat(manageUsersPage.getPageTitle(), equalTo(ManageUsersPage.PAGE_TITLE));

        // Click "Create New Account" button
        manageUsersPage.clickCreateNewAccount();

        // Check fields on the "Create New Account" view
        List<String> expectedFields = manageUsersPage.getExpectedFormFields();
        List<WebElement> actualFields = manageUsersPage.getActualFormFields();

        assertThat("Count of expected fields not equal to count of actual fields",
                expectedFields.size(), equalTo(actualFields.size()));

        SoftAssert softAssert = new SoftAssert();
        for (String expectedFieldId : expectedFields) {
            softAssert.assertTrue(manageUsersPage.existActualField(expectedFieldId), "Field with id='" + expectedFieldId + "' not exist");
        }
        softAssert.assertAll();

        // Fill user inforamtion
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src\\test\\resources\\selenium\\NewUser.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username = properties.getProperty("username") + RandomStringUtils.randomNumeric(6);
        String realname = properties.getProperty("realname");
        String email = username + properties.getProperty("email");
        String password = properties.getProperty("password");
        String verifyPassword = properties.getProperty("verify.password", password);
        String accessLevel = properties.getProperty("access.level");
        boolean accountEnabled = Boolean.parseBoolean(properties.getProperty("enabled"));
        boolean accountProtected = Boolean.parseBoolean(properties.getProperty("protected"));

        manageUsersPage.setAllFields(username, realname, email, password, verifyPassword, accessLevel,
                accountEnabled, accountProtected);

        // Click "Create User" button
        manageUsersPage.submitCreateUser();

        // Check added user info
        manageUsersPage.waitAfterCreateUser(5);
        manageUsersPage.selectMenuTabsItem(ManageTabItems.MANAGE_USERS);

        WebElement userInfo = manageUsersPage.findUserByName(username);

        assertThat("User with name " + username + " not found", userInfo, notNullValue());
        softAssert.assertEquals(manageUsersPage.getUserRealName(userInfo), realname);
        softAssert.assertEquals(manageUsersPage.getUserEmail(userInfo), email);
        softAssert.assertEquals(manageUsersPage.getUserAccessLevel(userInfo), accessLevel);
        softAssert.assertEquals(manageUsersPage.isEnabled(userInfo), accountEnabled);
        softAssert.assertEquals(manageUsersPage.isProtected(userInfo), accountProtected);
        softAssert.assertAll();

        // Logout
        manageUsersPage.logout();

        // Login under created user
        loginPage.login(username, password);

        // Assert User name in the right-top side of screen that user is loggined
        assertThat(homePage.getCurrUserName(), equalToIgnoringCase(username));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        // Logout
        homePage.logout();

        super.tearDown();
    }
}
