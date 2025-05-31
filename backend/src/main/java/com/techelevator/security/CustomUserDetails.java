package com.techelevator.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.techelevator.model.Users;

public class CustomUserDetails implements UserDetails {
    private final Users users;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Users users, Collection<? extends GrantedAuthority> authorities) {
        this.users = users;
        this.authorities = authorities;
    }

    public int getUserId() {
        return users.getUserId();
    }

    public String getRole() {
        return users.getRole();
    }

    public String getFullName() {
        return users.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return users.getPasswordHash();
    }

    @Override 
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

     @Override
    public boolean isAccountNonLocked() {
        return true;
    }

     @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

     @Override
    public boolean isEnabled() {
        return true;
    }
}
