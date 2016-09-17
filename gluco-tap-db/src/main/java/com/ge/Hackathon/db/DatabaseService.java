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

    private void checkMessageNeeded(int bloodGlucose, Patient patient) {
        if (bloodGlucose < patient.getLowerBound()) {
            System.out.println("1");
        } else if (bloodGlucose > patient.getUpperBound()) {
            System.out.println("2");
        }
    }

    public String parseUpload(String data) {
        String[] result = new String[3];
        for(int i = 0; i < result.length; i++) {
            String current = data.split("&")[i];
            result[i] = current.split("=")[1] + " ";
        }
        Reading reading = new Reading(Integer.parseInt(result[0].replaceAll(" ","")), result[2].replaceAll(" ",""), result[1].replaceAll(" ",""));
        if (patientRepository.exists(reading.getPatientId())) {
            Patient patient = patientRepository.getOne(reading.getPatientId());
            readingRepository.save(reading);
            patient.getReadings().add(reading);
            patientRepository.save(patient);
            checkMessageNeeded(reading.getGlucoseLevel(), patient);
        } else {
            readingRepository.save(reading);
            List<Reading> readingList = new ArrayList<Reading>();
            readingList.add(reading);
            Patient patient = new Patient("no first name", "no last name", reading.getPatientId(), readingList, 60, 400);
            patientRepository.save(patient);
            checkMessageNeeded(reading.getGlucoseLevel(), patient);
        }
        return data;
    }

    public void fillSampleData() {
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
        Patient patient = new Patient("jason", "gibson", "patient1", readingList, 60, 400);
        patientRepository.save(patient);
    }

    public Patient getPatientInfo(String patientId) {
        if (patientRepository.exists(patientId)) {
            return patientRepository.getOne(patientId);
        }
        return null;
    }

    public List<Reading> getAllReadings(String patientId) {
        return readingRepository.findByPatientId(patientId);
    }

    public void clear() {
        patientRepository.deleteAll();
        readingRepository.deleteAll();
    }
}
