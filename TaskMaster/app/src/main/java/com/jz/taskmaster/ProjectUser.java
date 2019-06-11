package com.jz.taskmaster;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ProjectUser {

    private String displayName;
    private String bio;

    @Exclude
    private String userId;

    public ProjectUser() {

    }

    public ProjectUser(String displayName, String bio) {
        this.displayName = displayName;
        this.bio = bio;
    }

    @Exclude
    public String getuserId() {
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
}
