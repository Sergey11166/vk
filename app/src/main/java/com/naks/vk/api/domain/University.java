package com.naks.vk.api.domain;

/**
 * Class describes university
 */
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

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", country=" + country +
                ", city=" + city +
                ", name='" + name + '\'' +
                ", faculty=" + faculty +
                ", facultyName='" + facultyName + '\'' +
                ", chair=" + chair +
                ", chairName='" + chairName + '\'' +
                ", graduation=" + graduation +
                ", educationForm='" + educationForm + '\'' +
                ", educationStatus='" + educationStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof University)) return false;

        University that = (University) o;

        if (getId() != that.getId()) return false;
        if (getCountry() != that.getCountry()) return false;
        if (getCity() != that.getCity()) return false;
        if (getFaculty() != that.getFaculty()) return false;
        if (getChair() != that.getChair()) return false;
        if (getGraduation() != that.getGraduation()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        if (getFacultyName() != null ? !getFacultyName().equals(that.getFacultyName()) : that.getFacultyName() != null)
            return false;
        if (getChairName() != null ? !getChairName().equals(that.getChairName()) : that.getChairName() != null)
            return false;
        if (getEducationForm() != null ? !getEducationForm().equals(that.getEducationForm()) : that.getEducationForm() != null)
            return false;
        return getEducationStatus() != null ? getEducationStatus().equals(that.getEducationStatus()) : that.getEducationStatus() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getCountry();
        result = 31 * result + (int) (getCity() ^ (getCity() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getFaculty();
        result = 31 * result + (getFacultyName() != null ? getFacultyName().hashCode() : 0);
        result = 31 * result + getChair();
        result = 31 * result + (getChairName() != null ? getChairName().hashCode() : 0);
        result = 31 * result + getGraduation();
        result = 31 * result + (getEducationForm() != null ? getEducationForm().hashCode() : 0);
        result = 31 * result + (getEducationStatus() != null ? getEducationStatus().hashCode() : 0);
        return result;
    }
}
