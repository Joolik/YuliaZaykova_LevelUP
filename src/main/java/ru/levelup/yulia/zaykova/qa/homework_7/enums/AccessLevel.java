package ru.levelup.yulia.zaykova.qa.homework_7.enums;

public enum AccessLevel {

    VIEWER(10, "viewer"),
    REPORTER(25, "reporter"),
    UPDATER(40, "updater"),
    DEVELOPER(55, "developer"),
    MANAGER(70, "manager"),
    ADMINISTRATOR(90, "administrator");

    private int id;
    private String name;

    AccessLevel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getNameById(final int id) {
        for (AccessLevel value : values()) {
            if (value.getId() == id) {
                return value.getName();
            }
        }
        throw new IllegalArgumentException("Unable to found value with id " + id);
    }
}
