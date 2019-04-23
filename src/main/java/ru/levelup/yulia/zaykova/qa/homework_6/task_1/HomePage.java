package ru.levelup.yulia.zaykova.qa.homework_6.task_1;

import org.openqa.selenium.WebDriver;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.base.BasePage;

public class HomePage extends BasePage {

    public static final String PAGE_TITLE = "My View - MantisBT";

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
