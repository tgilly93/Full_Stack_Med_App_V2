package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Staff;

public interface StaffDao {
    List<Staff> getAllStaff();

    Staff getStaffById(int staffId);

    Staff createStaff(Staff staff);

    boolean updateStaff(Staff staff);

    boolean deleteStaff(int staffId);
}
