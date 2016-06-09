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
     * 0 is not friend; 1 - is friend
     */
    @SerializedName("is_friend")
    private boolean isFriend;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }
}
