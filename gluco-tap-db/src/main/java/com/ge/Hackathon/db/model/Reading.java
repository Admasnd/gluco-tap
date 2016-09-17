package com.ge.Hackathon.db.model;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by JasonGibson on 9/16/16.
 */
@Entity
@Table
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int glucoseLevel;
    private String dateTime;
    private String patientId;

    public Reading() {

    }

    public Reading(int glucoseLevel, String dateTime, String patientId) {
        this.glucoseLevel = glucoseLevel;
        this.dateTime = dateTime;
        this.patientId = patientId;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
