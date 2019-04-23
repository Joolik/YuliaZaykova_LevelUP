package ru.levelup.yulia.zaykova.qa.homework_6.task_1.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.enums.LeftSideMenuItems;

import java.util.List;

public abstract class BasePage extends BaseWebComponent {

    @FindBy(className = "user-info")
    private WebElement userInfo;

    @FindBy(xpath = "//div[@id='sidebar']//a")
    private List<WebElement> menuLeftSide;

    protected BasePage(WebDriver driver) {
        super(driver);
    }

    // getTitle
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Get current user name
    public String getCurrUserName() {
        return userInfo.getText();
    }

    // Get left-side Menu
    public List<WebElement> getLeftSideMenu() {
        return menuLeftSide;
    }

    //Select left-side Menu item
    public void selectLeftSideMenuItem(LeftSideMenuItems menuItem) {
        for (WebElement we : menuLeftSide) {
            if (we.getText().trim().equals(menuItem.getValue())) {
                we.click();
                break;
            }
        }
    }

    // Logout
    public void logout() {
        userInfo.click();
        driver.findElement(By.xpath("//a[contains(text(), 'Logout')]")).click();
    }
}
