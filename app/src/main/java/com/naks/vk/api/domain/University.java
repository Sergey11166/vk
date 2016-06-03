package com.naks.vk.api.domain;

public class University {

    /**
     * university ID;
     */
    private int id;

    /**
     * ID of the country the university is located in
     */
    private int country;

    /**
     *  ID of the city the university is located in
     */
    private long city;

    /**
     * university name
     */
    private String name;

    /**
     *  faculty ID
     */
    private int faculty;

    /**
     * faculty name
     */
    private String facultyName;

    /**
     * university chair ID
     */
    private int chair;

    /**
     * chair name
     */
    private String chairName;

    /**
     * graduation year
     */
    private int graduation;

    /**
     * education form
     */
    private String educationForm;

    /**
     * education status
     */
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
