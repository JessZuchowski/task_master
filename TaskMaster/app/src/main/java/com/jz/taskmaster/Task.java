package com.jz.taskmaster;

public class Task {

    private String title;

    private String description;

    private String state;


    //to be part of state
    public boolean isAvailable;
    public boolean isAssigned;
    public boolean isAccepted;
    public boolean isFinished;

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
