package com.naks.vk.api.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Class describes military service of {@link User}
 */
public class Military {

    /**
     * Unit
     */
    private String unit;

    /**
     * Id of unit
     */
    @SerializedName("unit_id")
    private int unitId;

    /**
     * Id of country
     */
    @SerializedName("country_id")
    private int countryId;

    /**
     * Year of start military service
     */
    private int from;

    /**
     * Year of end military service
     */
    private int until;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
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

    @Override
    public String toString() {
        return "Military{" +
                "unit='" + unit + '\'' +
                ", unitId=" + unitId +
                ", countryId=" + countryId +
                ", from=" + from +
                ", until=" + until +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Military)) return false;

        Military military = (Military) o;

        if (getUnitId() != military.getUnitId()) return false;
        if (getCountryId() != military.getCountryId()) return false;
        if (getFrom() != military.getFrom()) return false;
        if (getUntil() != military.getUntil()) return false;
        return getUnit() != null ? getUnit().equals(military.getUnit()) : military.getUnit() == null;

    }

    @Override
    public int hashCode() {
        int result = getUnit() != null ? getUnit().hashCode() : 0;
        result = 31 * result + getUnitId();
        result = 31 * result + getCountryId();
        result = 31 * result + getFrom();
        result = 31 * result + getUntil();
        return result;
    }
}
