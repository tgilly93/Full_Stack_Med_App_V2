package com.techelevator.jdbcDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.PrescriptionInfoDao;
import com.techelevator.dto.PrescriptionInfoDto;

@Component
public class JdbcPrescriptionInfoDao implements PrescriptionInfoDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcPrescriptionInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PrescriptionInfoDto> prescriptionInfoRowMapper = (rs, rowNum) -> {
        PrescriptionInfoDto prescriptionInfo = new PrescriptionInfoDto();
        prescriptionInfo.setPrescriptionId(rs.getInt("Prescription ID"));
        prescriptionInfo.setPrescriptionName(rs.getString("Prescription Name"));
        prescriptionInfo.setDescription(rs.getString("Description"));
        prescriptionInfo.setCost(rs.getBigDecimal("Cost"));
        return prescriptionInfo;
    };

    @Override
    public List<PrescriptionInfoDto> getAllPrescriptionInfo() {
        String sql = "SELECT * FROM prescription_info";
        return jdbcTemplate.query(sql, prescriptionInfoRowMapper);
    }
}
