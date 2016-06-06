package com.naks.vk.api.domain;

import java.util.List;

/**
 * Class describes a user profile
 */
public class User {

    public static final int RELATION_NOT_SPECIFIED      = 0;
    public static final int RELATION_SINGLE             = 1;
    public static final int RELATION_IN_RELATIONSHIP    = 2;
    public static final int RELATION_ENGAGED            = 3;
    public static final int RELATION_MARRIED            = 4;
    public static final int RELATION_COMPLICATED        = 5;
    public static final int RELATION_ACTIVELY_SEARCHING = 6;
    public static final int RELATION_IN_LOVE            = 7;

    /**
     * Default fields
     */
    private long id;
    private String firstName;
    private String lastName;

    /**
     * returns if a profile is deleted or blocked. Gets the value deleted or banned.
     * Keep in mind that in this case no additional fields are returned
     */
    private String deactivated;

    /**
     * Optional fields
     */

    /**
     * format 'user_id+photo_id'
     */
    private String photoId;

    /**
     * returns 1 if the profile is verified, 0 if not.
     */
    private boolean verified;

    /**
     * user sex.  One of three values is returned:
     * 1 — female;
     * 2 — male;
     * 0 — not specified.
     */
    private int sex;

    /**
     * user's date of birth.  Returned as DD.MM.YYYY or DD.MM (if birth year is hidden).
     * If the whole date is hidden, no field is returned.
     */
    private String bdate;

    /**
     * ID of the city specified on user's page in "Contacts" section.
     * Returns city ID that can be used to get its name using places.getCityById method.
     * If no city is specified or main information on the page is hidden for in privacy settings,
     * then it returns 0.
     */
    private City city;

    /**
     * ID of the country specified on user's page in "Contacts" section.
     * Returns country ID that can be used to get its name using places.getCountryById method.
     * If no country is specified or main information on the page is hidden in privacy settings,
     * then it returns 0.
     */
    private Country country;

    /**
     * user's home town.
     */
    private String homeTown;

    /**
     * returns 1 if a current user have setup photo for profile.
     */
    private boolean hasPhoto;

    /**
     * returns URL of square photo of the user with 50 pixels in width.
     * In case user does not have a photo, http://vk.com/images/camera_c.gif is returned.
     */
    private String photo50;

    /**
     * returns URL of square photo of the user with 100 pixels in width.
     * In case user does not have a photo, http://vk.com/images/camera_b.gif is returned.
     */
    private String photo100;

    /**
     * returns URL of user's photo with 200 pixels in width.
     * In case user does not have a photo, http://vk.com/images/camera_a.gif is returned.
     */
    private String photo200Orig;

    /**
     * returns URL of square photo of the user with 200 pixels in width.
     * If the photo was uploaded long time ago, there can be no image of such size and in this case
     * the reply will not include this field.
     */
    private String photo200;

    /**
     * returns URL of user's photo with 400 pixels in width.
     * If user does not have a photo of such size, reply will not include this field.
     */
    private String photo400Origin;

    /**
     * returns URL of square photo of the user with maximum width.
     * Can be returned a photo both 200 and 100 pixels in width.
     * In case user does not have a photo, http://vk.com/images/camera_b.gif is returned.
     */
    private String photoMax;

    /**
     * returns URL of user's photo of maximum size.
     * Can be returned a photo both 400 and 200 pixels in width.
     * In case user does not have a photo, http://vk.com/images/camera_a.gif is returned.
     */
    private String photoMaxOrigin;

    /**
     * information whether the user is online.  Returned values: 1 — online, 0 — offline.
     * If user utilizes a mobile application or site mobile version, it returns online_mobile
     * additional field that includes 1. With that, in case of application, online_app additional
     * field is returned with application ID.
     */
    private boolean online;
    private boolean onlineMobile;

    /**
     * information about friend lists.  Returns IDs of friend lists the user is member of,
     * separated with a comma. The field is available for friends.get method only.
     * To get information about ID and names of friend lists use friends.getLists method.
     * If user is not a member of any friend list, then when accepting data in XML format
     * the respective <user> node does not contain <lists> tag.
     */
    private String lists;

    /**
     * page screen name.  Returns a string with a page screen name (only subdomain is returned, like andrew).
     * If not set, "id'+uid is returned, e.g. id35828305.
     */
    private String domain;

    /**
     * Information whether the user's mobile phone number is available.
     * Returned values: 1 — available, 0 — not available.
     * We recommend you to use it prior to call of secure.sendSMSNotification method.
     */
    private boolean hasMobile;

    /**
     * information about user's phone numbers.
     * If data are available and not hidden in privacy settings, the following fields are returned:
     * - mobile_phone — user's mobile phone number (only for standalone applications);
     * - home_phone — user's additional phone number.
     */
    private String mobilePhone;
    private String homePhone;

    /**
     * returns a website address from a user profile.
     */
    private String site;

    /**
     * information about user's higher education institution.  The following fields are returned:
     * university — university ID;
     * university_name — university name;
     * faculty — faculty ID;
     * faculty_name — faculty name;
     * graduation — graduation year.
     */
    private int university;
    private String universityName;
    private int faculty;
    private String facultyName;
    private int graduation;
    private String educationForm;
    private String educationStatus;

    /**
     * List of higher education institutions where user studied.
     * Returns universities array with {@link University} objects
     */
    private List<University> universities;

    /**
     * List of schools where user studied in.  Returns schools array with {@link School} objects
     */
    private List<School> schools;

    /**
     * user status.  Returns a string with status text that is in the profile below user's name
     */
    private String status;

    /**
     * last visit date.  Returns {@link LastSeen} object
     */
    private LastSeen lastSeen;

    /**
     * number of user's followers
     */
    private int followersCount;

    /**
     * number of common friends with a current user.
     */
    private int commonCount;

    /**
     * number of various objects the user has.  Can be used in users.get method only when
     * requesting information about a user.
     * Returns an {@link Counters}object
     */
    private Counters counters;

    /**
     * current user's occupation. Returns {@link Occupation} object
     */
    private Occupation occupation;

    /**
     * User nickname (middle name)
     */
    private String nickname;

    /**
     * current user's relatives list. Returns a list of {@link Relative} objects
     */
    private List<Relative> relatives;

    /**
     * user relationship status. Returned values:
     * {@link #RELATION_SINGLE};
     * {@link #RELATION_IN_RELATIONSHIP};
     * {@link #RELATION_ENGAGED};
     * {@link #RELATION_MARRIED};
     * {@link #RELATION_COMPLICATED};
     * {@link #RELATION_ACTIVELY_SEARCHING};
     * {@link #RELATION_IN_LOVE};
     */
    private int relation;

    /**
     * If in {@link #relation} specified vk user, that return {@link RelationPartner} object
     */
    private RelationPartner relationPartner;

    /**
     * {@link Personal} object
     */
    private Personal personal;

    /**
     * Name in Skype
     */
    private String skype;

    /**
     * Page in instagramm
     */
    private String instagram;

    /**
     * Id of page in facebook
     */
    private String facebook;

    /**
     * Name in facebook
     */
    private String facebookName;

    /**
     * wall comments allowed(1 — allowed, 0 — not allowed)
     */
    private boolean wallComments;

    /**
     * activities
     */
    private String activities;

    /**
     * interests
     */
    private String interests;

    /**
     * favorite music
     */
    private String music;

    /**
     * favorite movies
     */
    private String movies;

    /**
     * favorite TV shows
     */
    private String tv;

    /**
     * favorite books
     */
    private String books;

    /**
     * favorite games
     */
    private String games;

    /**
     * "About me"
     */
    private String about;

    /**
     * favorite quotes
     */
    private String quotes;

    /**
     * can post on the wall: 1 – allowed, 0 — not allowed
     */
    private boolean canPost;

    /**
     * can see other users' posts on the wall: 1 – allowed, 0 – not allowed
     */
    private boolean canSeeAllPosts;

    /**
     * can see other users' audio on the wall: 1 – allowed, 0 – not allowed
     */
    private boolean canSeeAudio;

    /**
     * can write private messages to a current user: 1 – allowed, 0 – not allowed
     */
    private boolean canWritePrivateMessage;

    /**
     * user time zone. Retuns only while requesting current user info
     */
    private int timezone;

    /**
     * user page's screen name (subdomain)
     */
    private String screenName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(String deactivated) {
        this.deactivated = deactivated;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public boolean isHasPhoto() {
        return hasPhoto;
    }

    public void setHasPhoto(boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
    }

    public String getPhoto50() {
        return photo50;
    }

    public void setPhoto50(String photo50) {
        this.photo50 = photo50;
    }

    public String getPhoto100() {
        return photo100;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }

    public String getPhoto200Orig() {
        return photo200Orig;
    }

    public void setPhoto200Orig(String photo200Orig) {
        this.photo200Orig = photo200Orig;
    }

    public String getPhoto200() {
        return photo200;
    }

    public void setPhoto200(String photo200) {
        this.photo200 = photo200;
    }

    public String getPhoto400Origin() {
        return photo400Origin;
    }

    public void setPhoto400Origin(String photo400Origin) {
        this.photo400Origin = photo400Origin;
    }

    public String getPhotoMax() {
        return photoMax;
    }

    public void setPhotoMax(String photoMax) {
        this.photoMax = photoMax;
    }

    public String getPhotoMaxOrigin() {
        return photoMaxOrigin;
    }

    public void setPhotoMaxOrigin(String photoMaxOrigin) {
        this.photoMaxOrigin = photoMaxOrigin;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isOnlineMobile() {
        return onlineMobile;
    }

    public void setOnlineMobile(boolean onlineMobile) {
        this.onlineMobile = onlineMobile;
    }

    public String getLists() {
        return lists;
    }

    public void setLists(String lists) {
        this.lists = lists;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isHasMobile() {
        return hasMobile;
    }

    public void setHasMobile(boolean hasMobile) {
        this.hasMobile = hasMobile;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getUniversity() {
        return university;
    }

    public void setUniversity(int university) {
        this.university = university;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
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

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LastSeen getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LastSeen lastSeen) {
        this.lastSeen = lastSeen;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getCommonCount() {
        return commonCount;
    }

    public void setCommonCount(int commonCount) {
        this.commonCount = commonCount;
    }

    public Counters getCounters() {
        return counters;
    }

    public void setCounters(Counters counters) {
        this.counters = counters;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Relative> relatives) {
        this.relatives = relatives;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public RelationPartner getRelationPartner() {
        return relationPartner;
    }

    public void setRelationPartner(RelationPartner relationPartner) {
        this.relationPartner = relationPartner;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

    public boolean isWallComments() {
        return wallComments;
    }

    public void setWallComments(boolean wallComments) {
        this.wallComments = wallComments;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public boolean isCanPost() {
        return canPost;
    }

    public void setCanPost(boolean canPost) {
        this.canPost = canPost;
    }

    public boolean isCanSeeAllPosts() {
        return canSeeAllPosts;
    }

    public void setCanSeeAllPosts(boolean canSeeAllPosts) {
        this.canSeeAllPosts = canSeeAllPosts;
    }

    public boolean isCanSeeAudio() {
        return canSeeAudio;
    }

    public void setCanSeeAudio(boolean canSeeAudio) {
        this.canSeeAudio = canSeeAudio;
    }

    public boolean isCanWritePrivateMessage() {
        return canWritePrivateMessage;
    }

    public void setCanWritePrivateMessage(boolean canWritePrivateMessage) {
        this.canWritePrivateMessage = canWritePrivateMessage;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (isVerified() != user.isVerified()) return false;
        if (getSex() != user.getSex()) return false;
        if (isHasPhoto() != user.isHasPhoto()) return false;
        if (isOnline() != user.isOnline()) return false;
        if (isOnlineMobile() != user.isOnlineMobile()) return false;
        if (isHasMobile() != user.isHasMobile()) return false;
        if (getUniversity() != user.getUniversity()) return false;
        if (getFaculty() != user.getFaculty()) return false;
        if (getGraduation() != user.getGraduation()) return false;
        if (getFollowersCount() != user.getFollowersCount()) return false;
        if (getCommonCount() != user.getCommonCount()) return false;
        if (getRelation() != user.getRelation()) return false;
        if (isWallComments() != user.isWallComments()) return false;
        if (isCanPost() != user.isCanPost()) return false;
        if (isCanSeeAllPosts() != user.isCanSeeAllPosts()) return false;
        if (isCanSeeAudio() != user.isCanSeeAudio()) return false;
        if (isCanWritePrivateMessage() != user.isCanWritePrivateMessage()) return false;
        if (getTimezone() != user.getTimezone()) return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        if (getDeactivated() != null ? !getDeactivated().equals(user.getDeactivated()) : user.getDeactivated() != null)
            return false;
        if (getPhotoId() != null ? !getPhotoId().equals(user.getPhotoId()) : user.getPhotoId() != null)
            return false;
        if (getBdate() != null ? !getBdate().equals(user.getBdate()) : user.getBdate() != null)
            return false;
        if (getCity() != null ? !getCity().equals(user.getCity()) : user.getCity() != null)
            return false;
        if (getCountry() != null ? !getCountry().equals(user.getCountry()) : user.getCountry() != null)
            return false;
        if (getHomeTown() != null ? !getHomeTown().equals(user.getHomeTown()) : user.getHomeTown() != null)
            return false;
        if (getPhoto50() != null ? !getPhoto50().equals(user.getPhoto50()) : user.getPhoto50() != null)
            return false;
        if (getPhoto100() != null ? !getPhoto100().equals(user.getPhoto100()) : user.getPhoto100() != null)
            return false;
        if (getPhoto200Orig() != null ? !getPhoto200Orig().equals(user.getPhoto200Orig()) : user.getPhoto200Orig() != null)
            return false;
        if (getPhoto200() != null ? !getPhoto200().equals(user.getPhoto200()) : user.getPhoto200() != null)
            return false;
        if (getPhoto400Origin() != null ? !getPhoto400Origin().equals(user.getPhoto400Origin()) : user.getPhoto400Origin() != null)
            return false;
        if (getPhotoMax() != null ? !getPhotoMax().equals(user.getPhotoMax()) : user.getPhotoMax() != null)
            return false;
        if (getPhotoMaxOrigin() != null ? !getPhotoMaxOrigin().equals(user.getPhotoMaxOrigin()) : user.getPhotoMaxOrigin() != null)
            return false;
        if (getLists() != null ? !getLists().equals(user.getLists()) : user.getLists() != null)
            return false;
        if (getDomain() != null ? !getDomain().equals(user.getDomain()) : user.getDomain() != null)
            return false;
        if (getMobilePhone() != null ? !getMobilePhone().equals(user.getMobilePhone()) : user.getMobilePhone() != null)
            return false;
        if (getHomePhone() != null ? !getHomePhone().equals(user.getHomePhone()) : user.getHomePhone() != null)
            return false;
        if (getSite() != null ? !getSite().equals(user.getSite()) : user.getSite() != null)
            return false;
        if (getUniversityName() != null ? !getUniversityName().equals(user.getUniversityName()) : user.getUniversityName() != null)
            return false;
        if (getFacultyName() != null ? !getFacultyName().equals(user.getFacultyName()) : user.getFacultyName() != null)
            return false;
        if (getEducationForm() != null ? !getEducationForm().equals(user.getEducationForm()) : user.getEducationForm() != null)
            return false;
        if (getEducationStatus() != null ? !getEducationStatus().equals(user.getEducationStatus()) : user.getEducationStatus() != null)
            return false;
        if (getUniversities() != null ? !getUniversities().equals(user.getUniversities()) : user.getUniversities() != null)
            return false;
        if (getSchools() != null ? !getSchools().equals(user.getSchools()) : user.getSchools() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(user.getStatus()) : user.getStatus() != null)
            return false;
        if (getLastSeen() != null ? !getLastSeen().equals(user.getLastSeen()) : user.getLastSeen() != null)
            return false;
        if (getCounters() != null ? !getCounters().equals(user.getCounters()) : user.getCounters() != null)
            return false;
        if (getOccupation() != null ? !getOccupation().equals(user.getOccupation()) : user.getOccupation() != null)
            return false;
        if (getNickname() != null ? !getNickname().equals(user.getNickname()) : user.getNickname() != null)
            return false;
        if (getRelatives() != null ? !getRelatives().equals(user.getRelatives()) : user.getRelatives() != null)
            return false;
        if (getRelationPartner() != null ? !getRelationPartner().equals(user.getRelationPartner()) : user.getRelationPartner() != null)
            return false;
        if (getPersonal() != null ? !getPersonal().equals(user.getPersonal()) : user.getPersonal() != null)
            return false;
        if (getSkype() != null ? !getSkype().equals(user.getSkype()) : user.getSkype() != null)
            return false;
        if (getInstagram() != null ? !getInstagram().equals(user.getInstagram()) : user.getInstagram() != null)
            return false;
        if (getFacebook() != null ? !getFacebook().equals(user.getFacebook()) : user.getFacebook() != null)
            return false;
        if (getFacebookName() != null ? !getFacebookName().equals(user.getFacebookName()) : user.getFacebookName() != null)
            return false;
        if (getActivities() != null ? !getActivities().equals(user.getActivities()) : user.getActivities() != null)
            return false;
        if (getInterests() != null ? !getInterests().equals(user.getInterests()) : user.getInterests() != null)
            return false;
        if (getMusic() != null ? !getMusic().equals(user.getMusic()) : user.getMusic() != null)
            return false;
        if (getMovies() != null ? !getMovies().equals(user.getMovies()) : user.getMovies() != null)
            return false;
        if (getTv() != null ? !getTv().equals(user.getTv()) : user.getTv() != null) return false;
        if (getBooks() != null ? !getBooks().equals(user.getBooks()) : user.getBooks() != null)
            return false;
        if (getGames() != null ? !getGames().equals(user.getGames()) : user.getGames() != null)
            return false;
        if (getAbout() != null ? !getAbout().equals(user.getAbout()) : user.getAbout() != null)
            return false;
        if (getQuotes() != null ? !getQuotes().equals(user.getQuotes()) : user.getQuotes() != null)
            return false;
        return getScreenName() != null ? getScreenName().equals(user.getScreenName()) : user.getScreenName() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getDeactivated() != null ? getDeactivated().hashCode() : 0);
        result = 31 * result + (getPhotoId() != null ? getPhotoId().hashCode() : 0);
        result = 31 * result + (isVerified() ? 1 : 0);
        result = 31 * result + getSex();
        result = 31 * result + (getBdate() != null ? getBdate().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getHomeTown() != null ? getHomeTown().hashCode() : 0);
        result = 31 * result + (isHasPhoto() ? 1 : 0);
        result = 31 * result + (getPhoto50() != null ? getPhoto50().hashCode() : 0);
        result = 31 * result + (getPhoto100() != null ? getPhoto100().hashCode() : 0);
        result = 31 * result + (getPhoto200Orig() != null ? getPhoto200Orig().hashCode() : 0);
        result = 31 * result + (getPhoto200() != null ? getPhoto200().hashCode() : 0);
        result = 31 * result + (getPhoto400Origin() != null ? getPhoto400Origin().hashCode() : 0);
        result = 31 * result + (getPhotoMax() != null ? getPhotoMax().hashCode() : 0);
        result = 31 * result + (getPhotoMaxOrigin() != null ? getPhotoMaxOrigin().hashCode() : 0);
        result = 31 * result + (isOnline() ? 1 : 0);
        result = 31 * result + (isOnlineMobile() ? 1 : 0);
        result = 31 * result + (getLists() != null ? getLists().hashCode() : 0);
        result = 31 * result + (getDomain() != null ? getDomain().hashCode() : 0);
        result = 31 * result + (isHasMobile() ? 1 : 0);
        result = 31 * result + (getMobilePhone() != null ? getMobilePhone().hashCode() : 0);
        result = 31 * result + (getHomePhone() != null ? getHomePhone().hashCode() : 0);
        result = 31 * result + (getSite() != null ? getSite().hashCode() : 0);
        result = 31 * result + getUniversity();
        result = 31 * result + (getUniversityName() != null ? getUniversityName().hashCode() : 0);
        result = 31 * result + getFaculty();
        result = 31 * result + (getFacultyName() != null ? getFacultyName().hashCode() : 0);
        result = 31 * result + getGraduation();
        result = 31 * result + (getEducationForm() != null ? getEducationForm().hashCode() : 0);
        result = 31 * result + (getEducationStatus() != null ? getEducationStatus().hashCode() : 0);
        result = 31 * result + (getUniversities() != null ? getUniversities().hashCode() : 0);
        result = 31 * result + (getSchools() != null ? getSchools().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getLastSeen() != null ? getLastSeen().hashCode() : 0);
        result = 31 * result + getFollowersCount();
        result = 31 * result + getCommonCount();
        result = 31 * result + (getCounters() != null ? getCounters().hashCode() : 0);
        result = 31 * result + (getOccupation() != null ? getOccupation().hashCode() : 0);
        result = 31 * result + (getNickname() != null ? getNickname().hashCode() : 0);
        result = 31 * result + (getRelatives() != null ? getRelatives().hashCode() : 0);
        result = 31 * result + getRelation();
        result = 31 * result + (getRelationPartner() != null ? getRelationPartner().hashCode() : 0);
        result = 31 * result + (getPersonal() != null ? getPersonal().hashCode() : 0);
        result = 31 * result + (getSkype() != null ? getSkype().hashCode() : 0);
        result = 31 * result + (getInstagram() != null ? getInstagram().hashCode() : 0);
        result = 31 * result + (getFacebook() != null ? getFacebook().hashCode() : 0);
        result = 31 * result + (getFacebookName() != null ? getFacebookName().hashCode() : 0);
        result = 31 * result + (isWallComments() ? 1 : 0);
        result = 31 * result + (getActivities() != null ? getActivities().hashCode() : 0);
        result = 31 * result + (getInterests() != null ? getInterests().hashCode() : 0);
        result = 31 * result + (getMusic() != null ? getMusic().hashCode() : 0);
        result = 31 * result + (getMovies() != null ? getMovies().hashCode() : 0);
        result = 31 * result + (getTv() != null ? getTv().hashCode() : 0);
        result = 31 * result + (getBooks() != null ? getBooks().hashCode() : 0);
        result = 31 * result + (getGames() != null ? getGames().hashCode() : 0);
        result = 31 * result + (getAbout() != null ? getAbout().hashCode() : 0);
        result = 31 * result + (getQuotes() != null ? getQuotes().hashCode() : 0);
        result = 31 * result + (isCanPost() ? 1 : 0);
        result = 31 * result + (isCanSeeAllPosts() ? 1 : 0);
        result = 31 * result + (isCanSeeAudio() ? 1 : 0);
        result = 31 * result + (isCanWritePrivateMessage() ? 1 : 0);
        result = 31 * result + getTimezone();
        result = 31 * result + (getScreenName() != null ? getScreenName().hashCode() : 0);
        return result;
    }
}
