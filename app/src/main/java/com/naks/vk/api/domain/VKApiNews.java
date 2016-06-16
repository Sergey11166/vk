package com.naks.vk.api.domain;

import com.vk.sdk.api.model.VKApiCommunityArray;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

/**
 * Class describes news
 */
public class VKApiNews {

    /**
     * News array for the current user.
     */
    public VKList<VKApiItem> items;

    /**
     *  Information about users in the newsfeed.
     */
    public VKList<VKApiUserFull> profiles;

    /**
     * Information about groups in the newsfeed.
     */
    public VKApiCommunityArray groups;

    /**
     * Contains a start_from parameter that is passed to get the next array of news.
     */
    public String next_from;
}
