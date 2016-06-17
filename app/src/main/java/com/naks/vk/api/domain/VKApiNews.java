package com.naks.vk.api.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.vk.sdk.api.model.VKApiCommunity;
import com.vk.sdk.api.model.VKApiModel;
import com.vk.sdk.api.model.VKApiUserFull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class describes news
 */
public class VKApiNews extends VKApiModel implements Parcelable {

    /**
     * News array for the current user.
     */
    public List<VKApiItem> items;

    /**
     *  Information about users in the newsfeed.
     */
    public List<VKApiUserFull> profiles;

    /**
     * Information about groups in the newsfeed.
     */
    public List<VKApiCommunity> groups;

    /**
     * Contains a start_from parameter that is passed to get the next array of news.
     */
    public String next_from;

    public VKApiNews(JSONObject from) throws JSONException {
        parse(from);
    }

    protected VKApiNews(Parcel in) {
        items = in.createTypedArrayList(VKApiItem.CREATOR);
        profiles = in.createTypedArrayList(VKApiUserFull.CREATOR);
        groups = in.createTypedArrayList(VKApiCommunity.CREATOR);
        next_from = in.readString();
    }

    public static final Creator<VKApiNews> CREATOR = new Creator<VKApiNews>() {
        @Override
        public VKApiNews createFromParcel(Parcel in) {
            return new VKApiNews(in);
        }

        @Override
        public VKApiNews[] newArray(int size) {
            return new VKApiNews[size];
        }
    };

    public VKApiNews parse(JSONObject source) throws JSONException {
        JSONArray items = source.optJSONArray("items");
        if (items != null) {
            this.items = new ArrayList<>();
            for (int i=0; i<items.length(); i++) {
                JSONObject item = (JSONObject) items.get(i);
                this.items.add(new VKApiItem(item));
            }
        }

        JSONArray profiles = source.optJSONArray("profiles");
        if (profiles != null) {
            this.profiles = new ArrayList<>();
            for (int i=0; i<profiles.length(); i++) {
                JSONObject profile = (JSONObject) profiles.get(i);
                this.profiles.add(new VKApiUserFull(profile));
            }
        }

        JSONArray groups = source.optJSONArray("groups");
        if (groups != null) {
            this.groups = new ArrayList<>();
            for (int i=0; i<groups.length(); i++) {
                JSONObject group = (JSONObject) groups.get(i);
                this.groups.add(new VKApiCommunity(group));
            }
        }

        next_from = source.optString("next_from");

        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(items);
        dest.writeTypedList(profiles);
        dest.writeTypedList(groups);
        dest.writeString(next_from);
    }
}
