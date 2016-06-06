package com.naks.vk.api.domain;

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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Counters)) return false;

        Counters counters = (Counters) o;

        return getAlbums() == counters.getAlbums() &&
                getVideos() == counters.getVideos() &&
                getAudios() == counters.getAudios() &&
                getPhotos() == counters.getPhotos() &&
                getNotes() == counters.getNotes() &&
                getFriends() == counters.getFriends() &&
                getGroups() == counters.getGroups() &&
                getOnlineFriends() == counters.getOnlineFriends() &&
                getMutualFriends() == counters.getMutualFriends() &&
                getUserVideos() == counters.getUserVideos() &&
                getFollowers() == counters.getFollowers() &&
                getPages() == counters.getPages();

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
        return result;
    }
}
