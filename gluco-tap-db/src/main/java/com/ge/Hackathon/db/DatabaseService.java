package com.ge.Hackathon.db;

import com.ge.Hackathon.db.model.Patient;
import com.ge.Hackathon.db.model.PatientRepository;
import com.ge.Hackathon.db.model.Reading;
import com.ge.Hackathon.db.model.ReadingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by JasonGibson on 9/16/16.
 */
@Service
public class DatabaseService {

    private final PatientRepository patientRepository;
    private final ReadingRepository readingRepository;

    public DatabaseService(PatientRepository patientRepository, ReadingRepository readingRepository) {
        this.patientRepository = patientRepository;
        this.readingRepository = readingRepository;
    }

    public String parseUpload(String data) {
        Reading r1 = new Reading(1, "12/10/96", "patient1");
        Reading r2 = new Reading(2, "12/10/96", "patient1");
        Reading r3 = new Reading(3, "12/10/96", "patient1");
        readingRepository.save(r1);
        readingRepository.save(r2);
        readingRepository.save(r3);
        List<Reading> readingList = new ArrayList<Reading>();
        readingList.add(r1);
        readingList.add(r2);
        readingList.add(r3);
        Patient patient = new Patient("jason", "gibson", "patient1", readingList);
        patientRepository.save(patient);
        return data;
    }

    public Patient getPatientInfo(String patientId) {
        return patientRepository.getOne(patientId);
    }

    public List<Reading> getAllReadings(String patientId) {
        return readingRepository.findByPatientId(patientId);
    }
}
