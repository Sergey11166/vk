package com.naks.vk.api.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Class describes contacts in {@link Group}
 */
public class Contact {

    /**
     * User id
     */
    @SerializedName("user_id")
    private int userId;

    /**
     * Description
     */
    private String desc;

    /**
     * Phone number
     */
    private String phone;

    /**
     * Email address
     */
    private String email;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "userId=" + userId +
                ", desc='" + desc + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (getUserId() != contact.getUserId()) return false;
        if (getDesc() != null ? !getDesc().equals(contact.getDesc()) : contact.getDesc() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(contact.getPhone()) : contact.getPhone() != null)
            return false;
        return getEmail() != null ? getEmail().equals(contact.getEmail()) : contact.getEmail() == null;

    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = 31 * result + (getDesc() != null ? getDesc().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }
}
