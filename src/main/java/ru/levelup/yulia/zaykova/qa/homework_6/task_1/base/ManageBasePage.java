package ru.levelup.yulia.zaykova.qa.homework_6.task_1.base;

import org.openqa.selenium.WebDriver;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.elements.MenuTabsElement;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.enums.ManageTabItems;

public abstract class ManageBasePage extends BasePage {

    private MenuTabsElement menuTabs;

    protected ManageBasePage(WebDriver driver) {
        super(driver);
        menuTabs = new MenuTabsElement(driver);
    }

    public void selectMenuTabsItem(ManageTabItems tabName) {
        menuTabs.clickTabByName(tabName);
    }
}
