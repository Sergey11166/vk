package com.naks.vk.api.domain;

/**
 * Class describes last visit date
 */
public class LastSeen {

    /**
     * last visit date (in Unix time)
     */
    private long time;

    /**
     * type of the platform that used for the last authorization.
     */
    private int platform;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "LastSeen{" +
                "time=" + time +
                ", platform=" + platform +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastSeen)) return false;

        LastSeen lastSeen = (LastSeen) o;

        if (getTime() != lastSeen.getTime()) return false;
        if (getPlatform() != lastSeen.getPlatform()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (getTime() ^ (getTime() >>> 32));
        result = 31 * result + getPlatform();
        return result;
    }
}
