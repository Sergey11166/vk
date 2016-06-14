package com.naks.vk.api.domain;

import com.google.gson.annotations.SerializedName;

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

    public static final int FRIEND_STATUS_NOT_FRIEND       = 0;
    public static final int FRIEND_STATUS_REQUEST_SENDED   = 1;
    public static final int FRIEND_STATUS_INCOMING_REQUEST = 2;
    public static final int FRIEND_STATUS_IS_FRIEND        = 3;

    /**
     * Default fields
     */
    private long id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
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
    @SerializedName("photo_id")
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
    @SerializedName("home_town")
    private String homeTown;

    /**
     * returns 1 if a current user have setup photo for profile.
     */
    @SerializedName("has_photo")
    private boolean hasPhoto;

    /**
     * returns URL of square photo of the user with 50 pixels in width.
     * In case user does not have a photo, http://vk.com/images/camera_c.gif is returned.
     */
    @SerializedName("photo_50")
    private String photo50;

    /**
     * returns URL of square photo of the user with 100 pixels in width.
     * In case user does not have a photo, http://vk.com/images/camera_b.gif is returned.
     */
    @SerializedName("photo_100")
    private String photo100;

    /**
     * returns URL of user's photo with 200 pixels in width.
     * In case user does not have a photo, http://vk.com/images/camera_a.gif is returned.
     */
    @SerializedName("photo_200_orig")
    private String photo200Orig;

    /**
     * returns URL of square photo of the user with 200 pixels in width.
     * If the photo was uploaded long time ago, there can be no image of such size and in this case
     * the reply will not include this field.
     */
    @SerializedName("photo_200")
    private String photo200;

    /**
     * returns URL of user's photo with 400 pixels in width.
     * If user does not have a photo of such size, reply will not include this field.
     */
    @SerializedName("photo_400_orig")
    private String photo400Origin;

    /**
     * returns URL of square photo of the user with maximum width.
     * Can be returned a photo both 200 and 100 pixels in width.
     * In case user does not have a photo, http://vk.com/images/camera_b.gif is returned.
     */
    @SerializedName("photo_max")
    private String photoMax;

    /**
     * returns URL of user's photo of maximum size.
     * Can be returned a photo both 400 and 200 pixels in width.
     * In case user does not have a photo, http://vk.com/images/camera_a.gif is returned.
     */
    @SerializedName("photo_max_orig")
    private String photoMaxOrigin;

    /**
     * information whether the user is online.  Returned values: 1 — online, 0 — offline.
     * If user utilizes a mobile application or site mobile version, it returns online_mobile
     * additional field that includes 1. With that, in case of application, online_app additional
     * field is returned with application ID.
     */
    private boolean online;

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
    @SerializedName("has_mobile")
    private boolean hasMobile;

    /**
     * information about user's phone numbers.
     * If data are available and not hidden in privacy settings, the following fields are returned:
     * - mobile_phone — user's mobile phone number (only for standalone applications);
     * - home_phone — user's additional phone number.
     */
    @SerializedName("mobile_phone")
    private String mobilePhone;

    @SerializedName("home_phone")
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

    @SerializedName("university_name ")
    private String universityName;
    private int faculty;

    @SerializedName("faculty_name")
    private String facultyName;
    private int graduation;

    @SerializedName("education_form")
    private String educationForm;

    @SerializedName("education_status")
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
    @SerializedName("last_seen")
    private LastSeen lastSeen;

    /**
     * number of user's followers
     */
    @SerializedName("followers_count")
    private int followersCount;

    /**
     * number of common friends with a current user.
     */
    @SerializedName("common_count")
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
    @SerializedName("relation_partner")
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
    @SerializedName("facebook_name")
    private String facebookName;

    /**
     * wall comments allowed(1 — allowed, 0 — not allowed)
     */
    @SerializedName("wall_comments")
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
    @SerializedName("can_see_all_posts")
    private boolean canSeeAllPosts;

    /**
     * can see other users' audio on the wall: 1 – allowed, 0 – not allowed
     */
    @SerializedName("can_see_audio")
    private boolean canSeeAudio;

    /**
     * can write private messages to a current user: 1 – allowed, 0 – not allowed
     */
    @SerializedName("can_write_private_message")
    private boolean canWritePrivateMessage;

    /**
     * can send friend request to a current user: 1 – allowed, 0 – not allowed
     */
    @SerializedName("can_send_friend_request")
    private boolean canSendFriendRequest;

    /**
     * if current user is favorite return 1
     */
    @SerializedName("is_favorite")
    private boolean isFavorite;

    /**
     * if user hidden from news returns 1 else 0
     */
    @SerializedName("is_hidden_from_feed")
    private boolean isHiddenFromFeed;

    /**
     * user time zone. Returns only while requesting current user info
     */
    private int timezone;

    /**
     * user page's screen name (subdomain)
     */
    @SerializedName("screen_name")
    private String screenName;

    /**
     * Maiden name
     */
    @SerializedName("maiden_name")
    private String maidenName;

    /**
     * Object {@link CropPhoto]
     */
    private CropPhoto cropPhoto;

    /**
     * 0 is not friend; 1 - is friend.
     */
    @SerializedName("is_friend")
    private boolean isFriend;

    /**
     * friend status.
     */
    @SerializedName("friend_status")
    private int friendStatus;

    /**
     * Object {@link Career}
     */
    private Career career;

    /**
     * Object {@link Military}
     */
    private Military military;

    /**
     * Is true if current user is blacklisted by this user, else is false.
     */
    private boolean blacklisted;

    /**
     * Is true if this user is blacklisted by current user, else is false.
     */
    private boolean blacklistedByName;

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

    public boolean isCanSendFriendRequest() {
        return canSendFriendRequest;
    }

    public void setCanSendFriendRequest(boolean canSendFriendRequest) {
        this.canSendFriendRequest = canSendFriendRequest;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isHiddenFromFeed() {
        return isHiddenFromFeed;
    }

    public void setHiddenFromFeed(boolean hiddenFromFeed) {
        isHiddenFromFeed = hiddenFromFeed;
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

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public CropPhoto getCropPhoto() {
        return cropPhoto;
    }

    public void setCropPhoto(CropPhoto cropPhoto) {
        this.cropPhoto = cropPhoto;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public int getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(int friendStatus) {
        this.friendStatus = friendStatus;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public Military getMilitary() {
        return military;
    }

    public void setMilitary(Military military) {
        this.military = military;
    }

    public boolean isBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    public boolean isBlacklistedByName() {
        return blacklistedByName;
    }

    public void setBlacklistedByName(boolean blacklistedByName) {
        this.blacklistedByName = blacklistedByName;
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

        return getId() == user.getId();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
