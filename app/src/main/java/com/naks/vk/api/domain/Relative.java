package com.naks.vk.api.domain;

/**
 * Class describes a Relative
 */
public class Relative {

    public static final String TYPE_SIBLING = "sibling ";
    public static final String TYPE_PARENT = "parent";
    public static final String TYPE_CHILD = "child";
    public static final String TYPE_GRANDPARENT = "grandparent";
    public static final String TYPE_GRANDCHILD = "grandchild";

    /**
     * User id. Name instead of id if a relative is not a VK user
     */
    private int id;

    /**
     * Relationship type:
     * {@link #TYPE_SIBLING}
     * {@link #TYPE_PARENT}
     * {@link #TYPE_CHILD}
     * {@link #TYPE_GRANDPARENT}
     * {@link #TYPE_GRANDCHILD}
     */
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

    @Override
    public String toString() {
        return "Relative{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relative)) return false;

        Relative relative = (Relative) o;

        if (getId() != relative.getId()) return false;
        if (getType() != null ? !getType().equals(relative.getType()) : relative.getType() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }
}
