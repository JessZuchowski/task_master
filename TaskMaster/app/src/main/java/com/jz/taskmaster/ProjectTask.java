package com.jz.taskmaster;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.HashSet;

@IgnoreExtraProperties
public class ProjectTask {

    private String title;
    private String description;
    private String state;

    @Exclude
    private String id;


//    private HashSet<Integer> states; ?
    //to be part of state
//    public boolean isAvailable;
//    public boolean isAssigned;
//    public boolean isAccepted;
//    public boolean isFinished;
//
    public ProjectTask() {

    }

    public ProjectTask(String title, String description, String state) {
        this.title = title;
        this.description = description;
        this.state = state;
    }


    public String getId() { return id;}
    public ProjectTask setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

}
