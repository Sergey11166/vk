package com.naks.vk.api.domain;

public class LastSeen {

    private long id;
    private int platform;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getPlatform() {
        return platform;
    }
    public void setPlatform(int platform) {
        this.platform = platform;
    }
}
