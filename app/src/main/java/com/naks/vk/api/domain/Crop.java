package com.naks.vk.api.domain;

/**
 * Class describes crop user photo
 */
public class Crop {

    /**
     * in percent
     */
    private int x;
    private int y;
    private int x2;
    private int y2;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return "Crop{" +
                "x=" + x +
                ", y=" + y +
                ", x2=" + x2 +
                ", y2=" + y2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Crop)) return false;

        Crop crop = (Crop) o;

        if (getX() != crop.getX()) return false;
        if (getY() != crop.getY()) return false;
        if (getX2() != crop.getX2()) return false;
        return getY2() == crop.getY2();

    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        result = 31 * result + getX2();
        result = 31 * result + getY2();
        return result;
    }
}
