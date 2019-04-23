package ru.levelup.yulia.zaykova.qa.homework_6.task_1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.base.BasePage;
import ru.levelup.yulia.zaykova.qa.homework_6.task_1.utils.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ViewIssuesPage extends BasePage {

    public static final String PAGE_TITLE = "View Issues - MantisBT";

    // Filter values
    @FindBy(id = "show_priority_filter")
    private WebElement linkPriority;
    @FindBy(name = "priority[]")
    private WebElement valuePriority;

    @FindBy(id = "show_severity_filter")
    private WebElement linkSeverity;
    @FindBy(name = "severity[]")
    private WebElement valueSeverity;

    @FindBy(id = "show_status_filter")
    private WebElement linkStatus;
    @FindBy(name = "status[]")
    private WebElement valueStatus;

    @FindBy(id = "do_filter_by_date_filter")
    private WebElement linkDateSubmitted;
    @FindBy(id = "use_date_filters")
    private WebElement checkboxDateSubmitted;
    @FindBy(name = "start_year")
    private WebElement valueStartYear;
    @FindBy(name = "start_month")
    private WebElement valueStartMonth;
    @FindBy(name = "start_day")
    private WebElement valueStartDay;
    @FindBy(name = "end_year")
    private WebElement valueEndYear;
    @FindBy(name = "end_month")
    private WebElement valueEndMonth;
    @FindBy(name = "end_day")
    private WebElement valueEndDay;

    // Button "Apply Filter"
    @FindBy(xpath = "//input[@name='filter_submit']")
    private WebElement btnApplyFilter;

    // Button "Reset"
    @FindBy(partialLinkText = "Reset")
    private WebElement btnReset;

    // List of issues
    @FindBy(xpath = "//table[@id='buglist']//tbody//tr")
    private List<WebElement> visibleBugList;


    public ViewIssuesPage(WebDriver driver) {
        super(driver);
    }

    public void setPriority(String value) {
        linkPriority.click();
        Helper.selectDropDownItem(valuePriority, value);
    }

    public void setSeverity(String value) {
        linkSeverity.click();
        Helper.selectDropDownItem(valueSeverity, value);
    }

    public void setStatus(String value) {
        linkStatus.click();
        Helper.selectDropDownItem(valueStatus, value);
    }

    public void setFilterByDateSubmitted(String startDate, String endDate) {
        linkDateSubmitted.click();

        Helper.setCheckbox(checkboxDateSubmitted, true);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Date newStartDate = null;
        Date newEndDate = null;
        try {
            newStartDate = formatter.parse(startDate);
            newEndDate = formatter.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter.applyPattern("y");
        Helper.selectDropDownItem(valueStartYear, formatter.format(newStartDate));
        Helper.selectDropDownItem(valueEndYear, formatter.format(newEndDate));
        formatter.applyPattern("MMMM");
        Helper.selectDropDownItem(valueStartMonth, formatter.format(newStartDate));
        Helper.selectDropDownItem(valueEndMonth, formatter.format(newEndDate));
        formatter.applyPattern("d");
        Helper.selectDropDownItem(valueStartDay, formatter.format(newStartDate));
        Helper.selectDropDownItem(valueEndDay, formatter.format(newEndDate));
    }

    public void submitApplyFilter() {
        btnApplyFilter.click();
    }

    public void filterReset() {
        btnReset.click();
    }

    public List<WebElement> getFilteredBugList() {
        return visibleBugList;
    }
}
