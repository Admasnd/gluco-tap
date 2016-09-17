package com.ge.Hackathon.db;

import com.ge.Hackathon.db.model.Patient;
import com.ge.Hackathon.db.model.Reading;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by JasonGibson on 9/16/16.
 */
@RestController
@RequestMapping("/upload")
public class DatabaseController {

    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String test() {
        return "Hello World!";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void remove() {
        databaseService.clear();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String uploadDataValue(@RequestBody String data) {
        return databaseService.parseUpload(data);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void storeSampleData() {
        databaseService.fillSampleData();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Patient getPatient(@PathVariable("id") String patientId) {
        return databaseService.getPatientInfo(patientId);
    }

    @RequestMapping(value = "/{id}/getAllReadings", method = RequestMethod.GET)
    public List<Reading> getAllReadings(@PathVariable("id") String patientId) {
        return databaseService.getAllReadings(patientId);
    }

    @RequestMapping(value = "/patientNames", method = RequestMethod.GET)
    public List<String> getNameList() {
        return databaseService.getNameList();
    }

    @RequestMapping(value = "/{id}/lower", method = RequestMethod.GET)
    public void setLower(@PathVariable("id") String patientId, @RequestParam int lower) {
        databaseService.saveLower(patientId, lower);
    }

    @RequestMapping(value = "/{id}/upper", method = RequestMethod.GET)
    public void setHigher(@PathVariable("id") String patientId, @RequestParam int higher) {
        databaseService.saveUpper(patientId, higher);
    }
}
