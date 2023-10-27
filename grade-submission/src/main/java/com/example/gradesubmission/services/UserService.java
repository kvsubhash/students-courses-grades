package com.example.gradesubmission.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gradesubmission.exceptions.UserNotFoundException;
import com.example.gradesubmission.models.User;
import com.example.gradesubmission.repos.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(Long userId) {
        return userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User getUser(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    
}
