package com.naks.vk.api.domain;

/**
 * Class describes school
 */
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

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", country=" + country +
                ", city=" + city +
                ", name='" + name + '\'' +
                ", yearFrom=" + yearFrom +
                ", yearTo=" + yearTo +
                ", yearGraduated=" + yearGraduated +
                ", schoolClass='" + schoolClass + '\'' +
                ", speciality='" + speciality + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School)) return false;

        School school = (School) o;

        if (getId() != school.getId()) return false;
        if (getCountry() != school.getCountry()) return false;
        if (getCity() != school.getCity()) return false;
        if (getYearFrom() != school.getYearFrom()) return false;
        if (getYearTo() != school.getYearTo()) return false;
        if (getYearGraduated() != school.getYearGraduated()) return false;
        if (getType() != school.getType()) return false;
        if (getName() != null ? !getName().equals(school.getName()) : school.getName() != null)
            return false;
        if (getSchoolClass() != null ? !getSchoolClass().equals(school.getSchoolClass()) : school.getSchoolClass() != null)
            return false;
        if (getSpeciality() != null ? !getSpeciality().equals(school.getSpeciality()) : school.getSpeciality() != null)
            return false;
        return getTypeName() != null ? getTypeName().equals(school.getTypeName()) : school.getTypeName() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (getCountry() ^ (getCountry() >>> 32));
        result = 31 * result + (int) (getCity() ^ (getCity() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getYearFrom();
        result = 31 * result + getYearTo();
        result = 31 * result + getYearGraduated();
        result = 31 * result + (getSchoolClass() != null ? getSchoolClass().hashCode() : 0);
        result = 31 * result + (getSpeciality() != null ? getSpeciality().hashCode() : 0);
        result = 31 * result + (int) (getType() ^ (getType() >>> 32));
        result = 31 * result + (getTypeName() != null ? getTypeName().hashCode() : 0);
        return result;
    }
}
