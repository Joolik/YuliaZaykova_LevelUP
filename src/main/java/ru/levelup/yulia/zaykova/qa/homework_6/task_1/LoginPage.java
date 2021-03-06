package ru.levelup.yulia.zaykova.qa.homework_6.task_1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.base.BasePage;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.entities.User;

public class LoginPage extends BasePage {

    public static final String PAGE_TITLE = "MantisBT";

    private WebElement username;

    private WebElement password;

    @FindBy(css = ".btn-success")
    private WebElement btnLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // TODO Лучше было бы передавать объект, для поддержки будет проще в дальнейшем
//    public void login(String username, String password) {
//        this.username.sendKeys(username);
//        btnLogin.click();
//        this.password.sendKeys(password);
//        btnLogin.click();
//    }

    public void login(User user) {
        this.username.sendKeys(user.getUsername());
        btnLogin.click();
        this.password.sendKeys(user.getPassword());
        btnLogin.click();
    }
}
