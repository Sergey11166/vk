package com.naks.vk.api.domain;

import com.google.gson.annotations.SerializedName;

/**
 * A photo object describes a photo and contains the following fields
 */
public class Photo {

    /**
     * Photo ID
     */
    private int pid;

    /**
     * Photo album ID
     */
    private int aid;

    /**
     * ID of the user or community that owns the photo
     */
    @SerializedName("owner_id")
    private int ownerId;

    /**
     * URL of image with maximum size 130x130px
     */
    private String src;

    /**
     * URL of image with maximum size 604x604px
     */
    @SerializedName("src_big")
    private String srcBig;

    /**
     * URL of image with maximum size 75x75px
     */
    @SerializedName("src_small")
    private String srcSmall;

    /**
     * URL of image with maximum size 807x807px
     */
    @SerializedName("src_xbig")
    private String srcXBig;

    /**
     * URL of image with maximum size 1280x1024px
     */
    @SerializedName("src_xxbig")
    private String srcXXBig;

    /**
     * URL of image with maximum size 2560x2048px
     */
    @SerializedName("src_xxxbig")
    private String srcXXXBig;

    /**
     * Width (in pixels) of the original photo
     */
    private int width;

    /**
     * Height (in pixels) of the original photo
     */
    private int height;

    /**
     * Text describing the photo
     */
    private int text;

    /**
     * Date (in Unix time) the photo was added
     */
    private int created;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrcBig() {
        return srcBig;
    }

    public void setSrcBig(String srcBig) {
        this.srcBig = srcBig;
    }

    public String getSrcSmall() {
        return srcSmall;
    }

    public void setSrcSmall(String srcSmall) {
        this.srcSmall = srcSmall;
    }

    public String getSrcXBig() {
        return srcXBig;
    }

    public void setSrcXBig(String srcXBig) {
        this.srcXBig = srcXBig;
    }

    public String getSrcXXBig() {
        return srcXXBig;
    }

    public void setSrcXXBig(String srcXXBig) {
        this.srcXXBig = srcXXBig;
    }

    public String getSrcXXXBig() {
        return srcXXXBig;
    }

    public void setSrcXXXBig(String srcXXXBig) {
        this.srcXXXBig = srcXXXBig;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "pid=" + pid +
                ", aid=" + aid +
                ", ownerId=" + ownerId +
                ", src='" + src + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", text=" + text +
                ", created=" + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;

        Photo photo = (Photo) o;

        return getPid() == photo.getPid();

    }

    @Override
    public int hashCode() {
        return getPid();
    }
}
