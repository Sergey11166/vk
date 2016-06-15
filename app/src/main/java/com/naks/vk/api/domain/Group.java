package com.naks.vk.api.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Class describes vk group
 */
public class Group {

    public static final int IS_CLOSED_OPEN    = 0;
    public static final int IS_CLOSED_CLOSED  = 1;
    public static final int IS_CLOSED_PRIVATE = 2;

    public static final int ADMIN_LEVEL_MODERATOR     = 1;
    public static final int ADMIN_LEVEL_EDITOR        = 2;
    public static final int ADMIN_LEVEL_ADMINISTRATOR = 3;

    public static final int MEMBER_STATUS_NOT_MEMBER = 0;
    public static final int MEMBER_STATUS_IS_MEMBER  = 1;
    public static final int MEMBER_STATUS_NOT_SURE   = 2;
    public static final int MEMBER_STATUS_REJECTED   = 3;
    public static final int MEMBER_STATUS_REQUESTED  = 4;
    public static final int MEMBER_STATUS_INVITED    = 5;

    /**
     * Group Id
     */
    private int id;

    /**
     * Group name
     */
    private String name;

    /**
     * Screen name of the community page (e.g. apiclub or club1).
     */
    @SerializedName("screen_name")
    private String screenName;

    /**
     * Whether the community is closed:
     * {@link #IS_CLOSED_OPEN}
     * {@link #IS_CLOSED_CLOSED}
     * {@link #IS_CLOSED_PRIVATE}
     */
    @SerializedName("is_closed")
    private int isClosed;

    /**
     * Whether a user is the community manager (0 — is not the manager, 1 — is the manager).
     */
    @SerializedName("is_admin")
    private boolean isAdmin;

    /**
     * (If is_admin=1) Rights of the user:
     * {@link #ADMIN_LEVEL_MODERATOR}
     * {@link #ADMIN_LEVEL_EDITOR}
     * {@link #ADMIN_LEVEL_ADMINISTRATOR}
     */
    @SerializedName("admin_level")
    private int adminLevel;

    /**
     * Whether a user is a community member (0 — is not а member, 1 — is a member).
     */
    @SerializedName("is_member")
    private boolean isMember;

    /**
     * Status of current user in this group:
     * {@link #MEMBER_STATUS_NOT_MEMBER}
     * {@link #MEMBER_STATUS_IS_MEMBER}
     * {@link #MEMBER_STATUS_NOT_SURE}
     * {@link #MEMBER_STATUS_REJECTED}
     * {@link #MEMBER_STATUS_REQUESTED}
     * {@link #MEMBER_STATUS_INVITED}
     */
    @SerializedName("member_status")
    private int memberStatus;

    /**
     * User id that invited current user in group
     */
    @SerializedName("invited_by")
    private int invitedBy;

    /**
     * Community type (group — group, page — public page, event — event).
     */
    private String type;

    /**
     * URL of the 50px-wide community logo.
     */
    @SerializedName("photo_50")
    private String photo50;

    /**
     * URL of the 100px-wide community logo.
     */
    @SerializedName("photo_100")
    private String photo100;

    /**
     * URL of the 200px-wide community logo.
     */
    @SerializedName("photo_200")
    private String photo200;

    /**
     * Object {@link City}
     */
    private City city;

    /**
     * Object {@link Country}
     */
    private Country country;

    /**
     * Object {@link Place}
     */
    private Place place;

    /**
     * Description
     */
    private String description;

    /**
     * Name of the home wiki-page of the community.
     */
    @SerializedName("wiki_page")
    private String wikiPage;

    /**
     * Number of community members.
     */
    @SerializedName("members_count")
    private int membersCount;

    /**
     * Counters object with community counters. May include any set of the following fields:
     * photos, albums, audios, videos, topics, docs.
     */
    private Counters counters;

    /**
     * Returned only for meeting and contain start/end time of the meeting as unixtime.
     */
    @SerializedName("start_date") private int startDate;
    @SerializedName("end_date") private int endDate;

    /**
     * Return if type group is page. Description for {@link #startDate}
     */
    @SerializedName("public_date_label")
    private String publicDateLabel;

    /**
     * Whether the current user can post on the community's wall (0 — cannot, 1 — can).
     */
    @SerializedName("can_post")
    private boolean canPost;

    /**
     * Whether the current user can message on the community's wall (0 — cannot, 1 — can).
     */
    @SerializedName("can_message")
    private boolean canMessage;


    /**
     * Whether others' posts on the community's wall can be viewed
     * (0 — cannot be viewed, 1 — can be viewed).
     */
    @SerializedName("can_see_all_posts")
    private boolean canSeeAllPosts;

    @SerializedName("can_upload_doc")
    private boolean canUploadDoc;

    @SerializedName("can_upload_video")
    private boolean canUploadVideo;

    @SerializedName("can_create_topic")
    private boolean canCreateTopic;

    /**
     * Returns the public page status bar. For groups, a string value is returned whether
     * the group is public or not; for events — start date.
     */
    private String activity;

    /**
     * Group status. Returns a string with status text that is on the group page below its name.
     */
    private String status;

    /**
     * Information from public page contact module.
     */
    private List<Contact> contacts;
}
