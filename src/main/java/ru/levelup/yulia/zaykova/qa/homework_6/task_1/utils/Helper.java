package ru.levelup.yulia.zaykova.qa.homework_6.task_1.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Helper {

    public static void selectDropDownItem(WebElement item, String itemText) {
        Select selectStatus = new Select(item);
        selectStatus.selectByVisibleText(itemText);
    }

    public static void setCheckbox(WebElement item, boolean selected) {
        // TODO !selected instead of (selected == false)
        // TODO selected instead of (selected == true)
        if ((item.isSelected() && (selected == false)) | (!item.isSelected() && (selected == true))) {
            item.findElement(By.xpath("./parent::label")).click();
        }
    }
}
