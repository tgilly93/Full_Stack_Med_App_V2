package com.techelevator.security;

import com.techelevator.dao.UserDao;
import com.techelevator.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserModelDetailsService.class);

    private final UserDao userDao;

    public UserModelDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating user '{}'", login);
        return createSpringSecurityUser(login, userDao.getUserByUsername(login));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String login, Users users) {
        List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(users.getRole()));

        return new org.springframework.security.core.userdetails.User(
            users.getUsername(),
            users.getPasswordHash(),
            grantedAuthorities
        );
    }
}

