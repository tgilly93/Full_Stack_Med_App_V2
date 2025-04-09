package com.techelevator.controller;

import com.techelevator.dao.OfficeDao;
import com.techelevator.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin //allows app to talk to API - MP
@RequestMapping("/api/offices")
public class OfficeController {
    @Autowired
    private OfficeDao officeDao;


    @GetMapping("/{officeId}")
    public Office getOfficeById(@PathVariable int officeId) {
        return officeDao.getOfficeById(officeId);
    }
//    // Get all offices
//    @GetMapping
//    public List<Office> getAllOffices() {
//        return officeDao.getAllOffices();
//    }
//
//    // Update an existing office (restricted to authorized roles)
//    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    @PutMapping("/{officeId}")
    public HttpStatus updateOffice(@PathVariable int officeId, @RequestBody Office office) {
        try {
            if (officeDao.updateOffice(office)) {
                return HttpStatus.OK;
            }
        }catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.NOT_FOUND;
    }
//
//    // Add a new office (restricted to authorized roles)
//    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
//    @PostMapping
//    public HttpStatus addOffice(@RequestBody Office office) {
//        if (officeDao.addOffice(office)) {
//            return HttpStatus.CREATED;
//        }
//        return HttpStatus.BAD_REQUEST;
//    }
}
