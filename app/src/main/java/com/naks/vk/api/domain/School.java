package com.naks.vk.api.domain;

public class School {

    /**
     *  school ID
     */
    private long id;

    /**
     * ID of the country the school is located in
     */
    private long country;

    /**
     * ID of the city the school is located in
     */
    private long city;

    /**
     * school name
     */
    private String name;

    /**
     * year the user started to study
     */
    private int yearFrom;

    /**
     *  year the user finished to study
     */
    private int yearTo;

    /**
     * graduation year
     */
    private int yearGraduated;

    /**
     * school class letter
     */
    private String schoolClass;

    /**
     * speciality
     */
    private String speciality;

    /**
     * type ID
     */
    private long type;

    /**
     * type name
     */
    private String typeName;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getCountry() {
        return country;
    }
    public void setCountry(long country) {
        this.country = country;
    }

    public long getCity() {
        return city;
    }
    public void setCity(long city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getYearFrom() {
        return yearFrom;
    }
    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getYearTo() {
        return yearTo;
    }
    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    public int getYearGraduated() {
        return yearGraduated;
    }
    public void setYearGraduated(int yearGraduated) {
        this.yearGraduated = yearGraduated;
    }

    public String getSchoolClass() {
        return schoolClass;
    }
    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public long getType() {
        return type;
    }
    public void setType(long type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
