package com.example.library_system.service;

import com.example.library_system.entity.User;
import com.example.library_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor for injecting UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Find user by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Find user by ID
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // Get all users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Save new user or update existing user
    public void save(User user) {
        userRepository.save(user);
    }

    // Update user data
    public void update(User user) {
        userRepository.save(user);
    }

    // Delete user by ID
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Register a new user with encoded password
    public void registerUser(User user) {
        // Encode the plain password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
