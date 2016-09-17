package com.ge.Hackathon.db;

import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.POST)
    public String uploadDataValue(@RequestBody String data) {
        return databaseService.parseUpload(data);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getSampleData() {
        return "Hello World!";
    }
}
