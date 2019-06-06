package com.jz.taskmaster;

import java.util.HashSet;

public class ProjectTask {

    private String title;
    private String description;
    private String state;

    public ProjectTask() {

    }

    public ProjectTask(String title, String description, String state) {
        this.title = title;
        this.description = description;
        this.state = state;
    }
//    private HashSet<Integer> states; ?
    //to be part of state
//    public boolean isAvailable;
//    public boolean isAssigned;
//    public boolean isAccepted;
//    public boolean isFinished;

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
