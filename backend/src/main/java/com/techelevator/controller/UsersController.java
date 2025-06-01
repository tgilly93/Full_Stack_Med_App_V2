package com.techelevator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.techelevator.model.Users;
import com.techelevator.service.UsersService;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN', 'ROLE_RECEPTIONIST')")
    @GetMapping("/id/{userId}")
    public Users getUserByUserId(@PathVariable int userId) {
        return usersService.getUserByUserId(userId);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Users createUser(@RequestBody Users user) {
        return usersService.createUser(user);
    }

    @PreAuthorize("#userId == authentication.principal.userId or hasRole('ROLE_ADMIN')")
    @PutMapping("/{userId}")
    public boolean updateUser(@PathVariable int userId, @RequestBody Users user) {
        user.setUserId(userId);
        return usersService.updateUser(user);
    }

    @PreAuthorize("#userId == authentication.principal.userId or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteUser(@PathVariable int userId) {
        return usersService.deleteUser(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/username/{username}")
    public Users getUserByUsername(@PathVariable String username) {
        return usersService.getUserByUsername(username);
    }
}
