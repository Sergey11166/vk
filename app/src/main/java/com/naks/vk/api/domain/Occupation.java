package com.naks.vk.api.domain;

/**
 * Class describes current user's occupation.
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
     * can take the values:
     * {@link #TYPE_WORK},
     * {@link #TYPE_SCHOOL},
     * {@link #TYPE_UNIVERSITY}
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

    @Override
    public String toString() {
        return "Occupation{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Occupation)) return false;

        Occupation that = (Occupation) o;

        if (getId() != that.getId()) return false;
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
