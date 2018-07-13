package com.gd.learn.angular.rest.middleware;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gd.learn.angular.rest.dao.UserRepository;
import com.gd.learn.angular.rest.model.User;

@Service
public class UserAccountService {
    private static Set<String> loggedInUsers = new HashSet<>();
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public boolean isAuthenticated(String credentials) {
        String[] parameters = credentials.split("&");
        String username = parameters[0];
        String password = parameters[1];
        if (username != null || password != null) {
            User usr = userRepository.findByUserName(username);
            if (usr != null && passwordEncoder.matches(password, usr.getPassword())) {
                loggedInUsers.add(username);
                return true;
            }
        }
        return false;
    }

    public List<User> getAllUser() {
        return userRepository.findAll()
                             .stream()
                             .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                             .get();
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String generateAccessToken(String credentials) {
        String[] parameters = credentials.split("&");
        String username = parameters[0];
        String password = parameters[1];
        return buildAccessToken(username, password);
    }

    private String buildAccessToken(String username, String password) {
        Base64.Encoder enc = Base64.getEncoder();
        String newUser = username + ":" + password;
        StringBuilder encodedByte = new StringBuilder();
        byte[] encBytes = enc.encode(newUser.getBytes());
        for (int i = 0; i < encBytes.length; i++) {
            char c = (char) encBytes[i];
            System.out.printf("%c", c);
            encodedByte.append(c);
        }
        return encodedByte.toString();
    }

    public boolean isLoggedIn(String username) {
        return loggedInUsers.contains(username);
    }

    public void logOutUser(String name) {
        loggedInUsers.remove(name);
    }
}
