package com.naks.vk.api.domain;

/**
 * last visit date
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
}
