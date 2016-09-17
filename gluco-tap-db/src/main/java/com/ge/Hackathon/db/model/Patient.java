package com.ge.Hackathon.db.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

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
    //@NotFound(action = NotFoundAction.IGNORE)
    private Set<Reading> readings;

    public Patient(String firstName, String lastName, String id, Set<Reading> readings) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.readings = readings;
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
    public Set<Reading> getReadings() {
        return readings;
    }

    public void setReadings(Set<Reading> readings) {
        this.readings = readings;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(firstName);
        stringBuffer.append(",");
        stringBuffer.append(lastName);
        stringBuffer.append(" ");
        stringBuffer.append(id);
        return stringBuffer.toString();
    }
}
