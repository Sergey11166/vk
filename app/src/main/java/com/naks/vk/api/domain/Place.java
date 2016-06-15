package com.naks.vk.api.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Class describes Place
 */
public class Place {

    /**
     * Place Id
     */
    private int id;

    /**
     * Place title;
     */
    private String title;

    /**
     * Place latitude
     */
    private double latitude;

    /**
     * Place longitude
     */
    private double longitude;

    /**
     * Something type
     */
    private int type;

    /**
     * Id of {@link Country}
     */
    @SerializedName("country")
    private int countryId;

    /**
     * Id of {@link City}
     */
    @SerializedName("city")
    private int cityId;

    /**
     * Address
     */
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", type=" + type +
                ", countryId=" + countryId +
                ", cityId=" + cityId +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;

        Place place = (Place) o;

        if (getId() != place.getId()) return false;
        if (Double.compare(place.getLatitude(), getLatitude()) != 0) return false;
        if (Double.compare(place.getLongitude(), getLongitude()) != 0) return false;
        if (getType() != place.getType()) return false;
        if (getCountryId() != place.getCountryId()) return false;
        if (getCityId() != place.getCityId()) return false;
        if (getTitle() != null ? !getTitle().equals(place.getTitle()) : place.getTitle() != null)
            return false;
        return getAddress() != null ? getAddress().equals(place.getAddress()) : place.getAddress() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        temp = Double.doubleToLongBits(getLatitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLongitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getType();
        result = 31 * result + getCountryId();
        result = 31 * result + getCityId();
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }
}
