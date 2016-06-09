package com.naks.vk.api.domain;

/**
 * Class describes cropped profile and mini photo
 */
public class CropPhoto {

    /**
     * Object {@link Photo}
     */
    private Photo photo;

    /**
     * Object {@link Crop}
     */
    private Crop crop;

    /**
     * Object {@link Rect}
     */
    private Rect rect;

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    @Override
    public String toString() {
        return "CropPhoto{" +
                "photo=" + photo +
                ", crop=" + crop +
                ", rect=" + rect +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CropPhoto)) return false;

        CropPhoto cropPhoto = (CropPhoto) o;

        if (getPhoto() != null ? !getPhoto().equals(cropPhoto.getPhoto()) : cropPhoto.getPhoto() != null)
            return false;
        if (getCrop() != null ? !getCrop().equals(cropPhoto.getCrop()) : cropPhoto.getCrop() != null)
            return false;
        return getRect() != null ? getRect().equals(cropPhoto.getRect()) : cropPhoto.getRect() == null;

    }

    @Override
    public int hashCode() {
        int result = getPhoto() != null ? getPhoto().hashCode() : 0;
        result = 31 * result + (getCrop() != null ? getCrop().hashCode() : 0);
        result = 31 * result + (getRect() != null ? getRect().hashCode() : 0);
        return result;
    }
}
