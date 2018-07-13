package com.gd.learn.angular.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gd.learn.angular.rest.config.CustomUserDetails;
import com.gd.learn.angular.rest.middleware.UserAccountService;
import com.gd.learn.angular.rest.model.User;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserAccountService userAccountService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userAccountService.getUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new CustomUserDetails(user);
    }
}
