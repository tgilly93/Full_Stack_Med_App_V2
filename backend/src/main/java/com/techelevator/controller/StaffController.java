package com.techelevator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Staff;
import com.techelevator.service.StaffService;

@RestController
@CrossOrigin
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/{staffId}")
    public Staff getStaffById(@PathVariable int staffId) {
        return staffService.getStaffById(staffId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Staff createStaff(@RequestBody Staff staff) {
        return staffService.createStaff(staff);
    }

    @PutMapping("/{staffId}")
    public boolean updateStaff(@PathVariable int staffId, @RequestBody Staff staff) {
        staff.setStaffId(staffId);
        return staffService.updateStaff(staff);
    }

    @DeleteMapping("/{staffId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteStaff(@PathVariable int staffId) {
        return staffService.deleteStaff(staffId);
    }
}
