package com.ge.Hackathon.db;

import com.ge.Hackathon.db.model.Patient;
import com.ge.Hackathon.db.model.PatientRepository;
import com.ge.Hackathon.db.model.Reading;
import com.ge.Hackathon.db.model.ReadingRepository;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
            sendMessage("your blood sugar is too low");
        } else if (bloodGlucose > patient.getUpperBound()) {
            sendMessage("your blood sugar is too high");
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
            Patient patient = new Patient("no first name", "no last name", reading.getPatientId(), readingList, 60, 400, 0, "gender not set",
                    0.0, "not set", null);
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
        Patient patient = new Patient("jason", "gibson", "patient1", readingList, 60, 400, 19 ,"Male", 230.0, "moderate", null);
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

    public List<String> getNameList() {
        List<String> names = new ArrayList<String>();
        for(Patient current : patientRepository.findAll()) {
            names.add(current.getLastName() + ", " + current.getFirstName());
        }
        return names;
    }

    private final String accountSID = "AC3ceb5731f004a47d4232bbb946e460b3";
    private final String authToken = "b51d78fa9c7bbb9ba1b134ad1ca19e27";

    public void sendMessage(String message) {
        TwilioRestClient client = new TwilioRestClient(accountSID, authToken);

        // Build a filter for the MessageList
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Body", message));
        params.add(new BasicNameValuePair("To", "+12405053437"));
        params.add(new BasicNameValuePair("From", "+15854548472"));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message textMessage = null;
        try {
            textMessage = messageFactory.create(params);
        } catch (TwilioRestException e) {
            e.printStackTrace();
        }
        System.out.println(textMessage.getSid());
    }
}
