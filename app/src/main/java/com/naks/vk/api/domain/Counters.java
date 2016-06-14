package com.naks.vk.api.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Class describes of various objects the user has.  Can be used in users.get method only when requesting information about a user.
 * Returns an object
 */
public class Counters {

    /**
     * number of photo albums
     */
    private int albums;

    /**
     * number of videos
     */
    private int videos;

    /**
     * number of audios
     */
    private int audios;

    /**
     * number of photos
     */
    private int photos;

    /**
     * number of notes
     */
    private int notes;

    /**
     * number of friends
     */
    private int friends;

    /**
     * number of communities
     */
    private int groups;

    /**
     * number of online friends
     */
    @SerializedName("online_friends")
    private int onlineFriends;

    /**
     * number of mutual friends
     */
    @SerializedName("mutual_friends")
    private int mutualFriends;

    /**
     * number of videos the user is tagged on
     */
    @SerializedName("user_videos")
    private int userVideos;

    /**
     * number of followers
     */
    private int followers;

    /**
     * number of objects in unit "Interesting pages"
     */
    private int pages;

    /**
     * number of topics
     */
    private int topics;

    /**
     * Number of docs
     */
    private int docs;

    public int getAlbums() {
        return albums;
    }

    public void setAlbums(int albums) {
        this.albums = albums;
    }

    public int getVideos() {
        return videos;
    }

    public void setVideos(int videos) {
        this.videos = videos;
    }

    public int getAudios() {
        return audios;
    }

    public void setAudios(int audios) {
        this.audios = audios;
    }

    public int getPhotos() {
        return photos;
    }

    public void setPhotos(int photos) {
        this.photos = photos;
    }

    public int getNotes() {
        return notes;
    }

    public void setNotes(int notes) {
        this.notes = notes;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public int getGroups() {
        return groups;
    }

    public void setGroups(int groups) {
        this.groups = groups;
    }

    public int getOnlineFriends() {
        return onlineFriends;
    }

    public void setOnlineFriends(int onlineFriends) {
        this.onlineFriends = onlineFriends;
    }

    public int getMutualFriends() {
        return mutualFriends;
    }

    public void setMutualFriends(int mutualFriends) {
        this.mutualFriends = mutualFriends;
    }

    public int getUserVideos() {
        return userVideos;
    }

    public void setUserVideos(int userVideos) {
        this.userVideos = userVideos;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTopics() {
        return topics;
    }

    public void setTopics(int topics) {
        this.topics = topics;
    }

    public int getDocs() {
        return docs;
    }

    public void setDocs(int docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        return "Counters{" +
                "albums=" + albums +
                ", videos=" + videos +
                ", audios=" + audios +
                ", photos=" + photos +
                ", notes=" + notes +
                ", friends=" + friends +
                ", groups=" + groups +
                ", onlineFriends=" + onlineFriends +
                ", mutualFriends=" + mutualFriends +
                ", userVideos=" + userVideos +
                ", followers=" + followers +
                ", pages=" + pages +
                ", topics=" + topics +
                ", docs=" + docs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Counters)) return false;

        Counters counters = (Counters) o;

        if (getAlbums() != counters.getAlbums()) return false;
        if (getVideos() != counters.getVideos()) return false;
        if (getAudios() != counters.getAudios()) return false;
        if (getPhotos() != counters.getPhotos()) return false;
        if (getNotes() != counters.getNotes()) return false;
        if (getFriends() != counters.getFriends()) return false;
        if (getGroups() != counters.getGroups()) return false;
        if (getOnlineFriends() != counters.getOnlineFriends()) return false;
        if (getMutualFriends() != counters.getMutualFriends()) return false;
        if (getUserVideos() != counters.getUserVideos()) return false;
        if (getFollowers() != counters.getFollowers()) return false;
        if (getPages() != counters.getPages()) return false;
        if (getTopics() != counters.getTopics()) return false;
        return getDocs() == counters.getDocs();

    }

    @Override
    public int hashCode() {
        int result = getAlbums();
        result = 31 * result + getVideos();
        result = 31 * result + getAudios();
        result = 31 * result + getPhotos();
        result = 31 * result + getNotes();
        result = 31 * result + getFriends();
        result = 31 * result + getGroups();
        result = 31 * result + getOnlineFriends();
        result = 31 * result + getMutualFriends();
        result = 31 * result + getUserVideos();
        result = 31 * result + getFollowers();
        result = 31 * result + getPages();
        result = 31 * result + getTopics();
        result = 31 * result + getDocs();
        return result;
    }
}
