package com.naks.vk.api.domain;

import com.google.gson.annotations.SerializedName;

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
    private String photo;

    /**
     * URL of the 100px-wide community logo.
     */
    @SerializedName("photo_medium")
    private String photoMedium;

    /**
     * URL of the 200px-wide community logo.
     */
    @SerializedName("photo_big")
    private String photoBig;
}
