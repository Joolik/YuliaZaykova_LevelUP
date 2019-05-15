package ru.levelup.yulia.zaykova.qa.homework_7.enums;

public enum Status {

    DEVELOPMENT(10, "development"),
    RELEASE(30, "release"),
    STABLE(50, "stable"),
    OBSOLETE(70, "obsolete");

    private int id;
    private String name;

    Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static int getIdByName(String name){
        for (Status value : values()) {
            if (value.getName().equals(name)) {
                return value.getId();
            }
        }
        return -1;
    }
}
