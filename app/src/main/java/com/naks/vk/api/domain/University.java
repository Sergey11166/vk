package com.naks.vk.api.domain;

public class University {

    private int id;
    private int country;
    private long city;
    private String name;
    private int faculty;
    private String facultyName;
    private int chair;
    private String chairName;
    private int graduation;
    private String educationForm;
    private String educationStatus;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCountry() {
        return country;
    }
    public void setCountry(int country) {
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

    public int getFaculty() {
        return faculty;
    }
    public void setFaculty(int faculty) {
        this.faculty = faculty;
    }

    public String getFacultyName() {
        return facultyName;
    }
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getChair() {
        return chair;
    }
    public void setChair(int chair) {
        this.chair = chair;
    }

    public String getChairName() {
        return chairName;
    }
    public void setChairName(String chairName) {
        this.chairName = chairName;
    }

    public int getGraduation() {
        return graduation;
    }
    public void setGraduation(int graduation) {
        this.graduation = graduation;
    }

    public String getEducationForm() {
        return educationForm;
    }
    public void setEducationForm(String educationForm) {
        this.educationForm = educationForm;
    }

    public String getEducationStatus() {
        return educationStatus;
    }
    public void setEducationStatus(String educationStatus) {
        this.educationStatus = educationStatus;
    }
}
