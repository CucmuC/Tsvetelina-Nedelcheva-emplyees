package com.example.demospring.model;

import java.util.Date;

public class Cowork {
    private long empl1Id;

    private long empl2Id;

    private long projectId;

    private long duration;

    public long getEmpl1Id() {
        return empl1Id;
    }

    public void setEmpl1Id(long empl1Id) {
        this.empl1Id = empl1Id;
    }

    public long getEmpl2Id() {
        return empl2Id;
    }

    public void setEmpl2Id(long empl2Id) {
        this.empl2Id = empl2Id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Cowork(long empl1Id, long empl2Id, long projectId,   long duration) {
        this.empl1Id = empl1Id;
        this.empl2Id = empl2Id;
        this.projectId = projectId;
        this.duration = duration;
    }
}
