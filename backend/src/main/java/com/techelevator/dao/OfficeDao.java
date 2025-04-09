package com.techelevator.dao;

import com.techelevator.model.Office;

import java.text.ParseException;

public interface OfficeDao {
    Office getOfficeById(int officeId);
    boolean updateOffice(Office office) throws ParseException;
}

