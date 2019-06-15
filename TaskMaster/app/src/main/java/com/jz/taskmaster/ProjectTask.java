package com.jz.taskmaster;

import android.database.Observable;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import androidx.databinding.ObservableBoolean;


@IgnoreExtraProperties
public class ProjectTask {

    private String title;
    private String description;
    public ObservableBoolean available;
    public ObservableBoolean assigned;
    public ObservableBoolean accepted;
    public ObservableBoolean accomplished;

    @Exclude
    private String id;

    public ProjectTask() {

    }

    public ProjectTask(String title, String description, ObservableBoolean available, ObservableBoolean assigned, ObservableBoolean accepted, ObservableBoolean accomplished) {
        this.title = title;
        this.description = description;
        this.available = available;
        this.assigned = assigned;
        this.accepted = accepted;
        this.accomplished = accomplished;

    }


    @Exclude
    public String getId() { return id;}
    @Exclude
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

    public ObservableBoolean getAvailable() {
        return available;
    }

    public void setAvailable(ObservableBoolean available) {
        this.available = available;
    }

    public ObservableBoolean getAssigned() {
        return assigned;
    }

    public void setAssigned(ObservableBoolean assigned) {
        this.assigned = assigned;
    }

    public ObservableBoolean getAccepted() {
        return accepted;
    }

    public void setAccepted(ObservableBoolean accepted) {
        this.accepted = accepted;
    }

    public ObservableBoolean getAccomplished() {
        return accomplished;
    }

    public void setAccomplished(ObservableBoolean accomplished) {
        this.accomplished = accomplished;
    }

}
