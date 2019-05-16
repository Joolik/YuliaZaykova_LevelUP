package ru.levelup.yulia.zaykova.qa.homework_6.task_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.base.ManageBasePage;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.entities.User;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.enums.ManageTabItems;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.utils.Helper;

import java.util.*;

public class ManageUsersPage extends ManageBasePage {

    public static final String PAGE_TITLE = "Manage Users - MantisBT";

    @FindBy(partialLinkText = "Create New Account")
    private WebElement btnNewAccount;

    @FindBy(xpath = "//div[@class='widget-body']//table//tr")
    private List<WebElement> actualFormFields;

    @FindBy(xpath = "//div[contains(@class,'col-md-12' )]/div[contains(@class, 'widget-box')]//table//thead//a")
    private List<WebElement> listColumnNames;

    private Map<String, Integer> tableHead;

    @FindBy(xpath = "//div[contains(@class,'col-md-12' )]/div[contains(@class, 'widget-box')]//table//tbody/tr")
    List<WebElement> listUsers;

    // FORM
    // Fields
    @FindBy(id = "user-username")
    private WebElement userName;
    @FindBy(id = "user-realname")
    private WebElement realName;
    @FindBy(id = "email-field")
    private WebElement email;
    @FindBy(id = "user-password")
    private WebElement password;
    @FindBy(id = "user-verify-password")
    private WebElement verifyPassword;
    @FindBy(id = "user-access-level")
    private WebElement accessLevel;
    @FindBy(id = "user-enabled")
    private WebElement userEnabled;
    @FindBy(id = "user-protected")
    private WebElement userProtected;
    // Submit button
    @FindBy(xpath = "//input[@type='submit' and contains(@value,'Create User')]")
    private WebElement btnCreateUser;

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    public void clickCreateNewAccount() {
        btnNewAccount.click();
    }

    public List<String> getExpectedFormFields() {
        List<String> expectedFields = new ArrayList();
        expectedFields.add("user-username");
        expectedFields.add("user-realname");
        expectedFields.add("email-field");
        expectedFields.add("user-password");
        expectedFields.add("user-verify-password");
        expectedFields.add("user-access-level");
        expectedFields.add("user-enabled");
        expectedFields.add("user-protected");
        return expectedFields;
    }

    public List<WebElement> getActualFormFields() {
        return actualFormFields;
    }

    public boolean existActualField(String fieldId) {
        if (driver.findElements(By.id(fieldId)).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // TODO Лучше было бы передавать объект, для поддержки будет проще в дальнейшем
//    public void setAllFields(String userName, String realName, String email, String password, String verifyPassword,
//                             String accessLevel, boolean userEnabled, boolean userProtected) {
//        this.userName.sendKeys(userName);
//        this.realName.sendKeys(realName);
//        this.email.sendKeys(email);
//        this.password.sendKeys(password);
//        this.verifyPassword.sendKeys(verifyPassword);
//        Helper.selectDropDownItem(this.accessLevel, accessLevel);
//        Helper.setCheckbox(this.userEnabled, userEnabled);
//        Helper.setCheckbox(this.userProtected, userProtected);
//    }

    public void setAllFields(User user) {
        this.userName.sendKeys(user.getUsername());
        this.realName.sendKeys(user.getRealname());
        this.email.sendKeys(user.getEmail());
        this.password.sendKeys(user.getPassword());
        this.verifyPassword.sendKeys(user.getVerifyPassword());
        Helper.selectDropDownItem(this.accessLevel, user.getAccessLevel());
        Helper.setCheckbox(this.userEnabled, user.isUserEnabled());
        Helper.setCheckbox(this.userProtected, user.isUserProtected());
    }

    public void submitCreateUser() {
        btnCreateUser.click();
    }

    public WebElement findUserByName(String userName) {
        // Get index of column by column name
        tableHead = new HashMap<>();
        int indexOfColumn = 1;
        for (WebElement we : listColumnNames) {
            tableHead.put(we.getText().trim(), indexOfColumn);
            indexOfColumn++;
        }

        WebElement userInfo = null;
        for (WebElement we : listUsers) {
            if (we.findElement(By.xpath("./td[" + tableHead.get("Username") + "]")).getText().equals(userName)) {
                userInfo = we;
                break;
            }
        }
        return userInfo;
    }

    public String getUserRealName(WebElement user) {
        return user.findElement(By.xpath("./td[" + tableHead.get("Real Name") + "]")).getText();
    }

    public String getUserEmail(WebElement user) {
        return user.findElement(By.xpath("./td[" + tableHead.get("E-mail") + "]")).getText();
    }

    public String getUserAccessLevel(WebElement user) {
        return user.findElement(By.xpath("./td[" + tableHead.get("Access Level") + "]")).getText();
    }

    public String getUsername(WebElement user) {
        return user.findElement(By.xpath("./td[" + tableHead.get("Username") + "]")).getText();
    }

    public boolean isEnabled(WebElement user) {
        if (user.findElements(By.xpath("./td[" + tableHead.get("Enabled") + "]/i")).size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isProtected(WebElement user) {
        if (user.findElements(By.xpath("./td[" + tableHead.get("Protected") + "]/i")).size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void waitAfterCreateUser(int TimeInSeconds) {
        Wait<WebDriver> wait = new WebDriverWait(driver, TimeInSeconds, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(ManageTabItems.MANAGE_USERS.getValue())));

    }

}
