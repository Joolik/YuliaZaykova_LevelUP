package ru.levelup.yulia.zaykova.qa.homework_7.enums;

public enum ViewState {

    PUBLIC(10, "public"),
    PRIVATE(50, "private");

    private int id;
    private String name;

    ViewState(int id, String name) {
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
        for (ViewState value : values()) {
            if (value.getName().equals(name)) {
                return value.getId();
            }
        }
        return -1;
    }

}
