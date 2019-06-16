package com.jz.taskmaster;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class ProjectUser {

    private String displayName;
    private String bio;
    private List<String> deviceIds = new ArrayList<>();

    @Exclude
    private String userId;

    public ProjectUser() {

    }

    public ProjectUser(String displayName, String bio, List<String> deviceIds) {
        this.displayName = displayName;
        this.bio = bio;
        this.deviceIds = deviceIds;
    }

    @Exclude
    public String getUserId() {

        return userId;
    }
    @Exclude
    public ProjectUser setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getDisplayName() {

        return displayName;
    }
    public void setDisplayName(String displayName) {

        this.displayName = displayName;
    }

    public String getBio() {

        return bio;
    }
    public void setBio(String bio) {

        this.bio = bio;
    }

    public List<String> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }
}
