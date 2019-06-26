package com.jz.taskmaster;

import android.database.Observable;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import androidx.databinding.ObservableBoolean;


@IgnoreExtraProperties
public class ProjectTask {

    private String title;
    private String description;
    private String available;
    private String assigned;
    private String accepted;
    private String accomplished;


    @Exclude
    private String projectTaskId;

    public ProjectTask() {

    }

    public ProjectTask(String title, String description) {
        this.title = title;
        this.description = description;
    }


    @Exclude
    public String getProjectTaskId() { return projectTaskId;}

    @Exclude
    public ProjectTask withProjectTaskId(String projectTaskId) {
        this.projectTaskId = projectTaskId;
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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public String getAccomplished() {
        return accomplished;
    }

    public void setAccomplished(String accomplished) {
        this.accomplished = accomplished;
    }
}