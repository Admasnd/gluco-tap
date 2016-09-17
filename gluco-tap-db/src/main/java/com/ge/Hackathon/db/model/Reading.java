package com.ge.Hackathon.db.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by JasonGibson on 9/16/16.
 */
@Entity
@Table
public class Reading {

    @Id
    private String id;
    private int glucoseLevel;
    private String dateTime;

    public Reading(String id, int glucoseLevel, String dateTime) {
        this.id = id;
        this.glucoseLevel = glucoseLevel;
        this.dateTime = dateTime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGlucoseLevel() {
        return glucoseLevel;
    }

    public void setGlucoseLevel(int glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
