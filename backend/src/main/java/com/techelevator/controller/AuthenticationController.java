package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.ClinicianDao;
import com.techelevator.dao.PatientDao;
import com.techelevator.dao.StaffDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dto.LoginDto;
import com.techelevator.dto.LoginResponseDto;
import com.techelevator.dto.RegisterUserDto;
import com.techelevator.security.jwt.TokenProvider;
import org.springframework.web.server.ResponseStatusException;

/**
 * AuthenticationController is a class used for handling requests to authenticate Users.
 *
 * It depends on an instance of a UserDao for retrieving and storing user data. This is provided
 * through dependency injection.
 */
@RestController
@CrossOrigin
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDao userDao;
    private PatientDao patientDao;
    private ClinicianDao clinicianDao;
    private StaffDao staffDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao, PatientDao patientDao, ClinicianDao clinicianDao, StaffDao staffDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
        this.patientDao = patientDao;
        this.clinicianDao = clinicianDao;
        this.staffDao = staffDao;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public LoginResponseDto login(@Valid @RequestBody LoginDto loginDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, false);

            Users users = userDao.getUserByUsername(loginDto.getUsername());
            return new LoginResponseDto(jwt, users);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public Users register(@Valid @RequestBody RegisterUserDto newUser) {
        try {
            if (userDao.getUserByUsername(newUser.getUsername()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.");
            } else {
                Users users = userDao.createUser(
                        new Users(newUser.getUsername(),newUser.getPassword(), newUser.getRole(), newUser.getFirstName() + " " + newUser.getLastName(), newUser.getAddress(), newUser.getCity(), newUser.getStateCode(), newUser.getZIP())
                );

                if ("role_patient".equalsIgnoreCase(newUser.getRole())) {
                    Patient patient = new Patient();
                    patient.setUserId(users.getUserId());
                    patient.setPatientFirstName(newUser.getFirstName());
                    patient.setPatientLastName(newUser.getLastName());
                    patient.setPatientDateOfBirth(newUser.getDateOfBirth());
                    patient.setPatientAddress(newUser.getAddress());
                    patient.setPatientCity(newUser.getCity());
                    patient.setPatientState(newUser.getStateCode());
                    patient.setZipCode(newUser.getZIP());
                    patient.setPatientPhoneNumber(newUser.getPhoneNumber());

                    patientDao.createPatient(patient);    
                } else if ("role_clinician".equalsIgnoreCase(newUser.getRole())) {
                    if (newUser.getNpiNumber() == null || newUser.getPrimaryOffice() == null || newUser.getClinicianRatePerHour() == null) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required clinician fields.");
                    }

                    Staff staff = new Staff();
                    staff.setStaffFirstName(newUser.getFirstName());
                    staff.setStaffLastName(newUser.getLastName());
                    staff.setStaffPhoneNumber(newUser.getPhoneNumber());
                    staff.setStaffAddress(users.getAddress());
                    staff.setOfficeId(newUser.getPrimaryOffice());
                    
                    Staff createdStaff = staffDao.createStaff(staff);

                    Clinician clinician = new Clinician();
                    clinician.setNpiNumber(newUser.getNpiNumber());
                    clinician.setUserId(users.getUserId());
                    clinician.setStaffId(createdStaff.getStaffId());
                    clinician.setPrimaryOffice(newUser.getPrimaryOffice());
                    clinician.setClinicianRatePerHour(newUser.getClinicianRatePerHour());

                    clinicianDao.createClinician(clinician);
                }

                return users;
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User registration failed.");
        }
    }

}

