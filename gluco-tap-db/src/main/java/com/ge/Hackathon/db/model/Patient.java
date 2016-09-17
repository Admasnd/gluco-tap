package com.ge.Hackathon.db.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JasonGibson on 9/16/16.
 */
@Entity
@Table
public class Patient {
    private String firstName;
    private String lastName;
    @Id
    private String id;
    @OneToMany(cascade={CascadeType.ALL})
    private List<Reading> readings;
    private int lowerBound;
    private int upperBound;
    private int age;
    private String gender;
    private double weight;
    private String exercise;
    private String[] medications;

    public Patient() {

    }

    public Patient(String firstName, String lastName, String id, List<Reading> readings, int lowerBound, int upperBound,
                   int age, String gender, double weight, String exercise, String[] medications) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.readings = readings;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.exercise = exercise;
        this.medications = medications;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    public List<Reading> getReadings() {
        return readings;
    }

    public void setReadings(List<Reading> readings) {
        this.readings = readings;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String[] getMedications() {
        return medications;
    }

    public void setMedications(String[] medications) {
        this.medications = medications;
    }

    /*public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(firstName);
        stringBuffer.append(",");
        stringBuffer.append(lastName);
        stringBuffer.append(" ");
        stringBuffer.append(id);
        return stringBuffer.toString();
    }*/
}
