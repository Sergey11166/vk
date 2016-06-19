package com.naks.vk.api.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.vk.sdk.api.model.VKApiModel;
import com.vk.sdk.api.model.VKApiPost;
import com.vk.sdk.api.model.VKAttachments;
import com.vk.sdk.api.model.VKList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class describes field {@link VKApiNews#items} of {@link VKApiNews}
 */
public class VKApiItem extends VKApiModel implements Parcelable {

    /**
     * Type of newsfeed, based on the specified filters (e.g., post, photo).
     */
    public String type;

    /**
     * ID of the news source (positive number signifies a user;
     * negative number signifies a community).
     */
    public int source_id;

    /**
     * Time in unixtime format
     */
    public long date;

    /**
     * Post ID on the owner wall.
     */
    public int post_id;

    /**
     * Type post on wall (post or copy)
     */
    public String post_type;

    /**
     * Pass if post created by delete page;
     */
    public String final_post;

    /**
     * находится в записях со стен, если сообщение является копией сообщения с чужой стены, и
     * содержит идентификатор владельца стены, у которого было скопировано сообщение;
     */
    public int copy_owner_id;

    /**
     * находится в записях со стен, если сообщение является копией сообщения с чужой стены, и
     * содержит идентификатор скопированного сообщения на стене его владельца;
     */
    public int copy_post_id;

    /**
     * массив, содержащий историю репостов для записи. Возвращается только в том случае,
     * если запись является репостом. Каждый из объектов массива, в свою очередь,
     * является {@link VKApiPost} стандартного формата.
     */
    public VKList<VKApiPost> copy_history;

    /**
     * находится в записях со стен, если сообщение является копией сообщения с чужой стены, и
     * содержит дату скопированного сообщения;
     */
    public long copy_post_date;

    /**
     * Text of post on wall
     */
    public String text;

    /**
     * Return if current user can edit post, always is 1.
     */
    public boolean can_edit;

    /**
     * Return if current user can delete post, always is 1.
     */
    public boolean can_delete;

    public Comments comments;

    public Likes likes;

    public Reposts reposts;

    /**
     * Information about attachments to the post (photos, links, etc.), if any;
     */
    public VKAttachments attachments = new VKAttachments();

    /**
     * Information about location.
     */
    public Geo geo;

    /**
     * photos, photo_tags, notes, friends — Information about the count of objects of corresponding
     * types (except wall posts) and up to the last 5 objects related to the current news.
     */
    public Photoss photos;
    public Photoss photo_tags;
    public Notes notes;
    public Friendss friends;

    public VKApiItem(JSONObject from) throws JSONException {
        parse(from);
    }

    public VKApiItem parse(JSONObject source) throws JSONException {
        type = source.optString("type");
        source_id = source.optInt("source_id");
        date = source.optLong("date");
        post_id = source.optInt("post_id");
        post_type = source.optString("post_type");
        final_post = source.optString("post_final");
        copy_owner_id = source.optInt("copy_owner_id");
        copy_post_id = source.optInt("copy_post");

        copy_history = new VKList<>(source.optJSONArray("copy_history"), VKApiPost.class);

        copy_post_date = source.optLong("copy_post_date");
        text = source.optString("text");
        can_edit = source.optInt("can_edit", 0) == 1;
        can_delete = source.optInt("can_delete", 0) == 1;

        JSONObject comments = source.optJSONObject("comments");
        if (comments != null) this.comments = new Comments(comments);

        JSONObject likes = source.optJSONObject("likes");
        if (likes != null) this.likes = new Likes(likes);

        JSONObject reposts = source.optJSONObject("reposts");
        if (reposts != null) this.reposts = new Reposts(reposts);

        attachments.fill(source.optJSONArray("attachments"));

        JSONObject geo = source.optJSONObject("geo");
        if (geo != null) this.geo = new Geo(geo);

        JSONObject photos = source.optJSONObject("photos");
        if (photos != null) this.photos = new Photoss(photos);

        JSONObject photo_tags = source.optJSONObject("photo_tags");
        if (photo_tags != null) this.photo_tags = new Photoss(photo_tags);

        JSONObject notes = source.optJSONObject("notes");
        if (notes != null) this.notes = new Notes(notes);

        JSONObject friends = source.optJSONObject("friends");
        if (friends != null) this.friends = new Friendss(friends);

        return this;
    }

    protected VKApiItem(Parcel in) {
        type = in.readString();
        source_id = in.readInt();
        date = in.readLong();
        post_id = in.readInt();
        post_type = in.readString();
        final_post = in.readString();
        copy_owner_id = in.readInt();
        copy_post_id = in.readInt();
        copy_history = in.readParcelable(VKList.class.getClassLoader());
        copy_post_date = in.readLong();
        text = in.readString();
        can_edit = in.readByte() != 0;
        can_delete = in.readByte() != 0;
        comments = in.readParcelable(Comments.class.getClassLoader());
        likes = in.readParcelable(Likes.class.getClassLoader());
        reposts = in.readParcelable(Reposts.class.getClassLoader());
        attachments = in.readParcelable(VKAttachments.class.getClassLoader());
        geo = in.readParcelable(Geo.class.getClassLoader());
        photos = in.readParcelable(Photoss.class.getClassLoader());
        photo_tags = in.readParcelable(Photoss.class.getClassLoader());
        notes = in.readParcelable(Notes.class.getClassLoader());
        friends = in.readParcelable(Friendss.class.getClassLoader());
    }

    public static final Creator<VKApiItem> CREATOR = new Creator<VKApiItem>() {
        @Override
        public VKApiItem createFromParcel(Parcel in) {
            return new VKApiItem(in);
        }

        @Override
        public VKApiItem[] newArray(int size) {
            return new VKApiItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeInt(source_id);
        dest.writeLong(date);
        dest.writeInt(post_id);
        dest.writeString(post_type);
        dest.writeString(final_post);
        dest.writeInt(copy_owner_id);
        dest.writeInt(copy_post_id);
        dest.writeParcelable(copy_history, flags);
        dest.writeLong(copy_post_date);
        dest.writeString(text);
        dest.writeByte((byte) (can_edit ? 1 : 0));
        dest.writeByte((byte) (can_delete ? 1 : 0));
        dest.writeParcelable(comments, flags);
        dest.writeParcelable(likes, flags);
        dest.writeParcelable(reposts, flags);
        dest.writeParcelable(attachments, flags);
        dest.writeParcelable(geo, flags);
        dest.writeParcelable(photos, flags);
        dest.writeParcelable(photo_tags, flags);
        dest.writeParcelable(notes, flags);
        dest.writeParcelable(friends, flags);
    }

    /**
     * Describes field {@link #comments}
     */
    public static class Comments implements Parcelable {
        public int count;
        public boolean can_post;

        public Comments(JSONObject comments) {
            this.count = comments.optInt("count");
            this.can_post = comments.optInt("can_post", 0) == 1;
        }

        protected Comments(Parcel in) {
            count = in.readInt();
            can_post = in.readByte() != 0;
        }

        public static final Creator<Comments> CREATOR = new Creator<Comments>() {
            @Override
            public Comments createFromParcel(Parcel in) {
                return new Comments(in);
            }

            @Override
            public Comments[] newArray(int size) {
                return new Comments[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(count);
            dest.writeByte((byte) (can_post ? 1 : 0));
        }
    }

    /**
     * Describes field {@link #likes}
     */
    public static class Likes implements Parcelable {
        public int count;
        public boolean user_like;
        public boolean can_like;
        public boolean can_publish;

        public Likes(JSONObject likes) {
            this.count = likes.optInt("count");
            this.user_like = likes.optInt("user_like", 0) == 1;
            this.can_like = likes.optInt("can_like", 0) == 1;
            this.can_publish = likes.optInt("can_publish", 0) == 1;
        }

        protected Likes(Parcel in) {
            count = in.readInt();
            user_like = in.readByte() != 0;
            can_like = in.readByte() != 0;
            can_publish = in.readByte() != 0;
        }

        public static final Creator<Likes> CREATOR = new Creator<Likes>() {
            @Override
            public Likes createFromParcel(Parcel in) {
                return new Likes(in);
            }

            @Override
            public Likes[] newArray(int size) {
                return new Likes[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(count);
            dest.writeByte((byte) (user_like ? 1 : 0));
            dest.writeByte((byte) (can_like ? 1 : 0));
            dest.writeByte((byte) (can_publish ? 1 : 0));
        }
    }

    /**
     * Describes field {@link #reposts}
     */
    public static class Reposts implements Parcelable {
        public int count;
        public boolean user_reposted;

        public Reposts(JSONObject reposts) {
            this.count = reposts.optInt("count");
            this.user_reposted = reposts.optInt("user_reposted", 0) == 1;
        }

        protected Reposts(Parcel in) {
            count = in.readInt();
            user_reposted = in.readByte() != 0;
        }

        public static final Creator<Reposts> CREATOR = new Creator<Reposts>() {
            @Override
            public Reposts createFromParcel(Parcel in) {
                return new Reposts(in);
            }

            @Override
            public Reposts[] newArray(int size) {
                return new Reposts[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(count);
            dest.writeByte((byte) (user_reposted ? 1 : 0));
        }
    }

    /**
     * Describes field {@link #geo}
     */
    public static class Geo implements Parcelable {
        public int place_id;
        public String title;
        public String type;
        public int country_id;
        public int city_id;
        public String address;
        public String showmap;

        public Geo(JSONObject geo) {
            this.place_id = geo.optInt("place_id");
            this.title = geo.optString("title");
            this.type = geo.optString("type");
            this.country_id = geo.optInt("country_id");
            this.city_id = geo.optInt("city_id");
            this.address = geo.optString("address");
            this.showmap = geo.optString("showmap");
        }

        protected Geo(Parcel in) {
            place_id = in.readInt();
            title = in.readString();
            type = in.readString();
            country_id = in.readInt();
            city_id = in.readInt();
            address = in.readString();
            showmap = in.readString();
        }

        public static final Creator<Geo> CREATOR = new Creator<Geo>() {
            @Override
            public Geo createFromParcel(Parcel in) {
                return new Geo(in);
            }

            @Override
            public Geo[] newArray(int size) {
                return new Geo[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(place_id);
            dest.writeString(title);
            dest.writeString(type);
            dest.writeInt(country_id);
            dest.writeInt(city_id);
            dest.writeString(address);
            dest.writeString(showmap);
        }
    }

    /**
     * Describes field {@link #photos} and {@link #photo_tags}
     */
    public static class Photoss implements Parcelable {
        public int id;
        public int owner_id;
        public int album_id;
        public String src;
        public String src_big;

        public Photoss(JSONObject photoss) {
            this.id = photoss.optInt("id");
            this.owner_id = photoss.optInt("owner_id");
            this.album_id = photoss.optInt("album_id");
            this.src = photoss.optString("src");
            this.src_big = photoss.optString("src_big");
        }

        protected Photoss(Parcel in) {
            id = in.readInt();
            owner_id = in.readInt();
            album_id = in.readInt();
            src = in.readString();
            src_big = in.readString();
        }

        public static final Creator<Photoss> CREATOR = new Creator<Photoss>() {
            @Override
            public Photoss createFromParcel(Parcel in) {
                return new Photoss(in);
            }

            @Override
            public Photoss[] newArray(int size) {
                return new Photoss[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(owner_id);
            dest.writeInt(album_id);
            dest.writeString(src);
            dest.writeString(src_big);
        }
    }

    /**
     * Describes field {@link #notes}
     */
    public static class Notes implements Parcelable {
        public int id;
        public int owner_id;
        public String title;
        public int comments;

        public Notes(JSONObject notes) {
            this.id = notes.optInt("id");
            this.owner_id = notes.optInt("owner_id");
            this.title = notes.optString("title");
            this.comments = notes.optInt("comments");
        }

        protected Notes(Parcel in) {
            id = in.readInt();
            owner_id = in.readInt();
            title = in.readString();
            comments = in.readInt();
        }

        public static final Creator<Notes> CREATOR = new Creator<Notes>() {
            @Override
            public Notes createFromParcel(Parcel in) {
                return new Notes(in);
            }

            @Override
            public Notes[] newArray(int size) {
                return new Notes[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(owner_id);
            dest.writeString(title);
            dest.writeInt(comments);
        }
    }

    /**
     * Describes field {@link #friends}
     */
    public  static class Friendss implements Parcelable {
        public int uid;

        public Friendss(JSONObject friendss) {
            this.uid = friendss.optInt("uid");
        }

        protected Friendss(Parcel in) {
            uid = in.readInt();
        }

        public static final Creator<Friendss> CREATOR = new Creator<Friendss>() {
            @Override
            public Friendss createFromParcel(Parcel in) {
                return new Friendss(in);
            }

            @Override
            public Friendss[] newArray(int size) {
                return new Friendss[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(uid);
        }
    }
}
