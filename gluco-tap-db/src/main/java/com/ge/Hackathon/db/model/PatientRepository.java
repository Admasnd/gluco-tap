package com.ge.Hackathon.db.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by JasonGibson on 9/17/16.
 */

public interface PatientRepository extends JpaRepository<Patient, String> {
}
