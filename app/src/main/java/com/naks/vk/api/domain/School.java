package com.naks.vk.api.domain;

public class School {

    private long id;
    private long country;
    private long city;
    private String name;
    private int yearFrom;
    private int yearTo;
    private int yearGraduated;
    private String schoolClass;
    private String speciality;
    private long type;
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
