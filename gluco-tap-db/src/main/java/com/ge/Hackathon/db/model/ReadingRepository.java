package com.ge.Hackathon.db.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by JasonGibson on 9/17/16.
 */
public interface ReadingRepository extends JpaRepository<Reading, UUID> {
    java.util.List<Reading> findByPatientId(String patientId);
}
