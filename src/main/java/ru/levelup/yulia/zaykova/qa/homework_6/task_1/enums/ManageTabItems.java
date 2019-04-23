package ru.levelup.yulia.zaykova.qa.homework_6.task_1.enums;

public enum ManageTabItems {
    MANAGE_USERS("Manage Users"),
    MANAGE_PROJECTS("Manage Projects"),
    MANAGE_TAGS("Manage Tags"),
    MANAGE_CUSTOM_FIELDS("Manage Custom Fields"),
    MANAGE_GLOBAL_PROFILES("Manage Global Profiles"),
    MANAGE_PLUGINS("Manage Plugins"),
    MANAGE_CONFIGURATION("Manage Configuration");

    private String value;

    ManageTabItems(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
