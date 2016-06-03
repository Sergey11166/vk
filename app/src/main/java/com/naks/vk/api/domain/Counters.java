package com.naks.vk.api.domain;

/**
 * number of various objects the user has.  Can be used in users.get method only when requesting information about a user.
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
    private int onlineFriends;

    /**
     * number of mutual friends
     */
    private int mutualFriends;

    /**
     * number of videos the user is tagged on
     */
    private int userVideos;

    /**
     * number of followers
     */
    private int followers;

    /**
     * number of objects in unit "Interesting pages"
     */
    private int pages;

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
}
