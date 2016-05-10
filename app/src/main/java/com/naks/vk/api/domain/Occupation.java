package com.naks.vk.api.domain;

public class Occupation {

    public static final String TYPE_WORK = "work";
    public static final String TYPE_SCHOOL = "school";
    public static final String TYPE_UNIVERSITY = "university";

    private int id;
    private String type;
    private String name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
