package ru.levelup.yulia.zaykova.qa.homework_6.task_1.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.base.BaseWebComponent;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.enums.ManageTabItems;

import java.util.List;

public class MenuTabsElement extends BaseWebComponent {

    @FindBy(xpath = "//ul[contains(@class, 'nav-tabs')]//a")
    private List<WebElement> menuTabs;

    public MenuTabsElement(WebDriver driver) {
        super(driver);
    }

    public void clickTabByName(ManageTabItems tabName) {
        for (WebElement we : menuTabs) {
            if (we.getText().trim().equals(tabName.getValue())) {
                we.click();
                break;
            }
        }
    }
}
