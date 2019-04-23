package ru.levelup.yulia.zaykova.qa.homework_6.task_1.enums;

public enum LeftSideMenuItems {
    MY_VIEW("My View"),
    VIEW_ISSUES("View Issues"),
    REPORT_ISSUE("Report Issue"),
    CHANGE_LOG("Change Log"),
    ROADMAP("Roadmap"),
    SUMMARY("Summary"),
    MANAGE("Manage");

    private String value;

    LeftSideMenuItems(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
