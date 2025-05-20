package com.techelevator.controller;

import com.techelevator.model.Office;
import com.techelevator.service.OfficeService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin 
@RequestMapping("/api/offices")
public class OfficeController {
    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping
    public List<Office> getAllOffices() {
        return officeService.getAllOffices();
    }
    
    @GetMapping("/{officeId}")
    public Office getOfficeById(@PathVariable int officeId) {
        return officeService.getOfficeById(officeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Office createOffice(@RequestBody Office office) {
        return officeService.createOffice(office);
    }

    @PutMapping("/{officeId}")
    public boolean updateOffice(@PathVariable int officeId, @RequestBody Office office) {
        office.setOfficeId(officeId);
        return officeService.updateOffice(office);
    }

    @DeleteMapping("/{officeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteOffice(@PathVariable int officeId) {
        return officeService.deleteOffice(officeId);
    }
}
