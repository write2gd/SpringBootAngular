package com.gd.learn.angular.rest.middleware;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.learn.angular.rest.dao.UserRepository;
import com.gd.learn.angular.rest.model.User;

@Service
public class UserAccountService {
    @Autowired
    protected UserRepository userRepository;

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
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
        userRepository.save(user);
    }

}
