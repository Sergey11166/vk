package com.naks.vk.mvp.model.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {

    private String content;

    protected News(Parcel in) {
        content = in.readString();
    }

    public News() {}

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
    }
}
