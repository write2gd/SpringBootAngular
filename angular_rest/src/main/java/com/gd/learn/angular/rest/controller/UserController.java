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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gd.learn.angular.rest.middleware.UserAccountService;
import com.gd.learn.angular.rest.model.GenericObject;
import com.gd.learn.angular.rest.model.User;

@RestController
public class UserController {
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/api/users")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<User> getUsers() {
        return userAccountService.getAllUser();
    }

    @GetMapping("api/users/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public User getUser(@PathVariable Long id) {
        return userAccountService.getUserById(id);
    }

    @GetMapping("users/authorize/{username}")
    @CrossOrigin(origins = "http://localhost:4200")
    public boolean isLoggedIn(@PathVariable String username) {
        return userAccountService.isLoggedIn(username);
    }

    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity addUser(@RequestBody User user) {
        try {
            userAccountService.saveUser(user);
            GenericObject<String> result = new GenericObject<>();
            result.setResult("User added successfully");
            result.setSuccess(true);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/api/users")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity updatedUser(@RequestBody User user) {
        try {
            userAccountService.saveUser(user);
            GenericObject<String> result = new GenericObject<>();
            result.setResult("OK");
            result.setSuccess(true);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity logIn(@RequestBody String credentials, HttpServletRequest request, HttpServletResponse response) {
        if (userAccountService.isAuthenticated(credentials)) {
            String token = userAccountService.generateAccessToken(credentials);
            GenericObject<String> result = new GenericObject<>();
            result.setResult("OK");
            result.setSuccess(true);
            result.setAccess_token(token);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            GenericObject<String> result = new GenericObject<>();
            result.setResult("Invalid Username or Password");
            result.setSuccess(false);
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "api/users/logout", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext()
                                                   .getAuthentication();
        if (auth != null) {
            userAccountService.logOutUser(auth.getName());
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
