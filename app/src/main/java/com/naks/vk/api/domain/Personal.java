package com.naks.vk.api.domain;

/**
 * Class to provide information from the "Personal views" section.
 */
public class Personal {

    public static final int POLITICAL_COMMUNIST = 1;
    public static final int POLITICAL_SOCIALIST = 2;
    public static final int POLITICAL_MODERATE = 3;
    public static final int POLITICAL_LIBERAL = 4;
    public static final int POLITICAL_CONSERVATIVE = 5;
    public static final int POLITICAL_MONARCHIST = 6;
    public static final int POLITICAL_ULTRACONSERVATIVE = 7;
    public static final int POLITICAL_APATHETIC = 8;
    public static final int POLITICAL_LIBERTARIAN = 9;

    public static final int PEOPLE_MAIN_INTELLECT_AND_CREATIVITY = 1;
    public static final int PEOPLE_MAIN_KINDNESS_AND_HONESTLY = 2;
    public static final int PEOPLE_MAIN_HEALTH_AND_BEAUTY = 3;
    public static final int PEOPLE_MAIN_WEALTH_AND_POWER = 4;
    public static final int PEOPLE_MAIN_COURAGE_AND_PERSISTENCE = 5;
    public static final int PEOPLE_MAIN_HUMOR_AND_LOVE_FOR_LIFE = 6;

    public static final int LIFE_MAIN_FAMILY_AND_CHILDREN = 1;
    public static final int LIFE_MAIN_CAREER_AND_MONEY = 2;
    public static final int LIFE_MAIN_ENTERTAINMENT_AND_LEISURE = 3;
    public static final int LIFE_MAIN_SCIENCE_AND_RESEARCH = 4;
    public static final int LIFE_MAIN_IMPROVING_THE_WORLD = 5;
    public static final int LIFE_MAIN_PERSONAL_DEVELOPMENT = 6;
    public static final int LIFE_MAIN_BEAUTY_AND_ART = 7;
    public static final int LIFE_MAIN_FAME_AND_INFLUENCE = 8;

    public static final int SMOKING_VERY_NEGATIVE = 1;
    public static final int SMOKING_NEGATIVE = 2;
    public static final int SMOKING_NEUTRAL = 3;
    public static final int SMOKING_COMPROMISABLE = 4;
    public static final int SMOKING_POSITIVE = 5;

    public static final int ALCOHOL_VERY_NEGATIVE = 1;
    public static final int ALCOHOL_NEGATIVE = 2;
    public static final int ALCOHOL_NEUTRAL = 3;
    public static final int ALCOHOL_COMPROMISABLE = 4;
    public static final int ALCOHOL_POSITIVE = 5;

    /**
     * Political views:
     * {@link #POLITICAL_COMMUNIST};
     * {@link #POLITICAL_SOCIALIST};
     * {@link #POLITICAL_MODERATE};
     * {@link #POLITICAL_LIBERAL};
     * {@link #POLITICAL_CONSERVATIVE};
     * {@link #POLITICAL_MONARCHIST};
     * {@link #POLITICAL_ULTRACONSERVATIVE};
     * {@link #POLITICAL_APATHETIC};
     * {@link #POLITICAL_LIBERTARIAN};
     */
    private int political;

    /**
     * languages
     */
    private String langs;

    private String religion;

    /**
     * inspired by
     */
    private String inspiredBy;

    /**
     * important in others:
     * {@link #PEOPLE_MAIN_INTELLECT_AND_CREATIVITY};
     * {@link #PEOPLE_MAIN_KINDNESS_AND_HONESTLY};
     * {@link #PEOPLE_MAIN_HEALTH_AND_BEAUTY};
     * {@link #PEOPLE_MAIN_WEALTH_AND_POWER};
     * {@link #PEOPLE_MAIN_COURAGE_AND_PERSISTENCE};
     * {@link #PEOPLE_MAIN_HUMOR_AND_LOVE_FOR_LIFE};
     */
    private int peopleMain;

    /**
     * personal priority:
     * {@link #LIFE_MAIN_FAMILY_AND_CHILDREN};
     * {@link #LIFE_MAIN_CAREER_AND_MONEY};
     * {@link #LIFE_MAIN_ENTERTAINMENT_AND_LEISURE};
     * {@link #LIFE_MAIN_SCIENCE_AND_RESEARCH};
     * {@link #LIFE_MAIN_IMPROVING_THE_WORLD};
     * {@link #LIFE_MAIN_PERSONAL_DEVELOPMENT};
     * {@link #LIFE_MAIN_BEAUTY_AND_ART};
     * {@link #LIFE_MAIN_FAME_AND_INFLUENCE};
     */
    private int lifeMain;

    /**
     * views on smoking:
     * {@link #SMOKING_VERY_NEGATIVE};
     * {@link #SMOKING_NEGATIVE};
     * {@link #SMOKING_NEUTRAL};
     * {@link #SMOKING_COMPROMISABLE};
     * {@link #SMOKING_POSITIVE};
     */
    private int smoking;

    /**
     * views on alcohol:
     * {@link #ALCOHOL_VERY_NEGATIVE};
     * {@link #ALCOHOL_NEGATIVE};
     * {@link #ALCOHOL_NEUTRAL};
     * {@link #ALCOHOL_COMPROMISABLE};
     * {@link #ALCOHOL_POSITIVE};
     */
    private int alcohol;

    public int getPolitical() {
        return political;
    }

    public void setPolitical(int political) {
        this.political = political;
    }

    public String getLangs() {
        return langs;
    }

    public void setLangs(String langs) {
        this.langs = langs;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getInspiredBy() {
        return inspiredBy;
    }

    public void setInspiredBy(String inspiredBy) {
        this.inspiredBy = inspiredBy;
    }

    public int getPeopleMain() {
        return peopleMain;
    }

    public void setPeopleMain(int peopleMain) {
        this.peopleMain = peopleMain;
    }

    public int getLifeMain() {
        return lifeMain;
    }

    public void setLifeMain(int lifeMain) {
        this.lifeMain = lifeMain;
    }

    public int getSmoking() {
        return smoking;
    }

    public void setSmoking(int smoking) {
        this.smoking = smoking;
    }

    public int getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(int alcohol) {
        this.alcohol = alcohol;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "political=" + political +
                ", langs='" + langs + '\'' +
                ", religion='" + religion + '\'' +
                ", inspiredBy='" + inspiredBy + '\'' +
                ", peopleMain=" + peopleMain +
                ", lifeMain=" + lifeMain +
                ", smoking=" + smoking +
                ", alcohol=" + alcohol +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personal)) return false;

        Personal personal = (Personal) o;

        if (getPolitical() != personal.getPolitical()) return false;
        if (getPeopleMain() != personal.getPeopleMain()) return false;
        if (getLifeMain() != personal.getLifeMain()) return false;
        if (getSmoking() != personal.getSmoking()) return false;
        if (getAlcohol() != personal.getAlcohol()) return false;
        if (getLangs() != null ? !getLangs().equals(personal.getLangs()) : personal.getLangs() != null)
            return false;
        if (getReligion() != null ? !getReligion().equals(personal.getReligion()) : personal.getReligion() != null)
            return false;
        if (getInspiredBy() != null ? !getInspiredBy().equals(personal.getInspiredBy()) : personal.getInspiredBy() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getPolitical();
        result = 31 * result + (getLangs() != null ? getLangs().hashCode() : 0);
        result = 31 * result + (getReligion() != null ? getReligion().hashCode() : 0);
        result = 31 * result + (getInspiredBy() != null ? getInspiredBy().hashCode() : 0);
        result = 31 * result + getPeopleMain();
        result = 31 * result + getLifeMain();
        result = 31 * result + getSmoking();
        result = 31 * result + getAlcohol();
        return result;
    }
}
