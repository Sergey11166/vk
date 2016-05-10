package com.naks.vk.api.domain;

import java.util.List;

public class User {

    public static final int RELATION_NOT_MARRIED   = 1;
    public static final int RELATION_HAS_FRIEND    = 2;
    public static final int RELATION_ENGAGED       = 3;
    public static final int RELATION_MARRIED       = 4;
    public static final int RELATION_DIFFICULT     = 5;
    public static final int RELATION_IN_SEARCH     = 6;
    public static final int RELATION_IN_LOVE       = 7;
    public static final int RELATION_NOT_SPECIFIED = 0;


    private long id;
    private String firstName;
    private String lastName;
    private String deactivated;
    private boolean hidden;
    private String photoId;
    private boolean verified;
    private int sex;
    private String bdate;
    private City city;
    private Country country;
    private String homeTown;
    private boolean hasPhoto;
    private String photo50;
    private String photo100;
    private String photo200Orig;
    private String photo200;
    private String photo400Origin;
    private String photoMax;
    private String photoMaxOrigin;
    private boolean online;
    private boolean onlineMobile;
    private long onlineApp;
    private String lists;
    private String domain;
    private boolean hasMobile;
    private String mobilePhone;
    private String homePhone;
    private String site;
    private int university;
    private String universityName;
    private int faculty;
    private String facultyName;
    private int graduation;
    private String educationForm;
    private String educationStatus;
    private List<University> universities;
    private List<School> schools;
    private String status;
    private LastSeen lastSeen;
    private int followersCount;
    private int commonCount;
    private Counters counters;
    private Occupation occupation;
    private String nickname;
    private List<Relative> relatives;
    private int relation;
    private RelationPartner relationPartner;
}
