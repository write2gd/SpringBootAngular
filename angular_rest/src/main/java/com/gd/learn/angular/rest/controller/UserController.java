package com.gd.learn.angular.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gd.learn.angular.rest.middleware.UserAccountService;
import com.gd.learn.angular.rest.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public List<User> getUsers() {
        return userAccountService.getAllUser();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public User getUser(@PathVariable Long id) {
        return userAccountService.getUserById(id);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext()
                                                   .getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
