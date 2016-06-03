package com.naks.vk.api.domain;

/**
 * current user's occupation.
 */
public class Occupation {

    public static final String TYPE_WORK = "work";
    public static final String TYPE_SCHOOL = "school";
    public static final String TYPE_UNIVERSITY = "university";

    /**
     * ID of school, university, company group (the one a user works in)
     */
    private int id;

    /**
     * can take the values: work, school, university
     */
    private String type;

    /**
     * name of school, university or work place
     */
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
