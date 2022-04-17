package com.example.demospring.model;

import java.util.Date;

public class Employee {


    private long id;

    private long projectID;

    private Date dateFrom;

    private Date dateTo;

    private long duration;

    public Employee() {
    }
    public Employee(long id, long projectID, Date dateFrom, Date dateTo,  long duration) {
        this.id = id;
        this.projectID = projectID;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectID() {
        return projectID;
    }

    public void setProjectID(long projectID) {
        this.projectID = projectID;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
