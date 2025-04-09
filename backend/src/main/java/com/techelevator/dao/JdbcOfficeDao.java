package com.techelevator.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Office;
@Component
public class JdbcOfficeDao implements OfficeDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcOfficeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Office getOfficeById(int officeId) {
        String sql = "SELECT office_id, office_name, office_phone_number, office_address, office_city, state, zip_code, office_open, office_close\n" +
                "FROM public.office WHERE office_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, officeId);
        if (results.next()) {
            return mapRowToOffice(results);
        }
        return null;
    }

//    @Override
//    public List<Office> getAllOffices() {
//        List<Office> offices = new ArrayList<>();
//        String sql = "SELECT * FROM Office";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        while (results.next()) {
//            offices.add(mapRowToOffice(results));
//        }
//        return offices;
//    }
@Override
    public boolean updateOffice(Office office) throws ParseException {
       String sql = "UPDATE Office SET office_name = ?, office_phone_number = ?, office_address = ?, office_city = ?, state = ?, zip_code = ?, office_open = ?, office_close = ? WHERE office_id = ?";
       DateFormat dateFormat = new SimpleDateFormat("hh:mm");
       Date formatedOpenTime = dateFormat.parse(office.getOfficeOpen());
       Date formattedCloseTime = dateFormat.parse(office.getOfficeClose());
       return jdbcTemplate.update(sql, office.getOfficeName(), office.getOfficePhoneNumber(), office.getOfficeAddress(), office.getOfficeCity(), office.getState(), office.getZipCode(), formatedOpenTime, formattedCloseTime, office.getOfficeId()) > 0;
    }
//
//    @Override
//    public boolean addOffice(Office office) {
//        String sql = "INSERT INTO Office (office_name, office_phone_number, office_address, office_city, state, zip_code, office_open, office_close) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        return jdbcTemplate.update(sql, office.getOfficeName(), office.getOfficePhoneNumber(), office.getOfficeAddress(), office.getOfficeCity(), office.getState(), office.getZipCode(), office.getOfficeOpen(), office.getOfficeClose()) > 0;
//    }

    private Office mapRowToOffice(SqlRowSet rs) {
        Office newOffice = new Office();
        newOffice.setOfficeId( rs.getInt("office_id"));
        newOffice.setOfficeName(rs.getString("office_name"));
        newOffice.setOfficePhoneNumber(rs.getString("office_phone_number"));
        newOffice.setOfficeAddress(rs.getString("office_address"));
        newOffice.setOfficeCity(rs.getString("office_city"));
        newOffice.setState(rs.getString("state"));
        newOffice.setZipCode(rs.getString("zip_code"));
        newOffice.setOfficeOpen(rs.getString("office_open"));
        newOffice.setOfficeClose(rs.getString("office_close"));

        return newOffice;

    }
}
