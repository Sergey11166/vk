package com.naks.vk.api.domain;

public class Relative {

    public static final String TYPE_SIBLING = "sibling ";
    public static final String TYPE_PARENT = "parent";
    public static final String TYPE_CHILD = "child";
    public static final String TYPE_GRANDPARENT = "grandparent";
    public static final String TYPE_GRANDCHILD = "grandchild";

    private int id;
    private String type;

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
}
