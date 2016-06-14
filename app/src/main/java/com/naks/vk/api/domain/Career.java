package com.naks.vk.api.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Class describes career of {@link User}
 */
public class Career {

    /**
     * Id of {@link Group} if available, else {@link #company}
     */
    @SerializedName("group_id")
    private int groupId;

    /**
     * Name of company if available, else {@link #groupId}
     */
    private String company;

    /**
     * Country id
     */
    @SerializedName("country_id")
    private int countryId;

    /**
     * Id of {@link City} if available, else {@link #cityName}
     */
    @SerializedName("city_id")
    private int cityId;

    /**
     * Name of city if available, else {@link #cityId}
     */
    @SerializedName("city_name")
    private String cityName;

    /**
     * Years of start work
     */
    private int from;

    /**
     * Years of end work
     */
    private int until;

    /**
     * Work position
     */
    private String position;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getUntil() {
        return until;
    }

    public void setUntil(int until) {
        this.until = until;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Career{" +
                "groupId=" + groupId +
                ", company='" + company + '\'' +
                ", countryId=" + countryId +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", from=" + from +
                ", until=" + until +
                ", position='" + position + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Career)) return false;

        Career career = (Career) o;

        if (getGroupId() != career.getGroupId()) return false;
        if (getCountryId() != career.getCountryId()) return false;
        if (getCityId() != career.getCityId()) return false;
        if (getFrom() != career.getFrom()) return false;
        if (getUntil() != career.getUntil()) return false;
        if (getCompany() != null ? !getCompany().equals(career.getCompany()) : career.getCompany() != null)
            return false;
        if (getCityName() != null ? !getCityName().equals(career.getCityName()) : career.getCityName() != null)
            return false;
        return getPosition() != null ? getPosition().equals(career.getPosition()) : career.getPosition() == null;

    }

    @Override
    public int hashCode() {
        int result = getGroupId();
        result = 31 * result + (getCompany() != null ? getCompany().hashCode() : 0);
        result = 31 * result + getCountryId();
        result = 31 * result + getCityId();
        result = 31 * result + (getCityName() != null ? getCityName().hashCode() : 0);
        result = 31 * result + getFrom();
        result = 31 * result + getUntil();
        result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
        return result;
    }
}
