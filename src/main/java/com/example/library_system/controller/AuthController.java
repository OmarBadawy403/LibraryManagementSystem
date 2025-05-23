package com.example.library_system.controller;

import com.example.library_system.entity.User;
import com.example.library_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Show the login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Show the homepage
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    // Display the registration form
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // This page includes role selection (ADMIN or USER)
    }

    // Handle user registration form submission
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String role) {

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

        // Register the user (password encryption is handled inside the service)
        userService.registerUser(user);

        return "redirect:/login?registered"; // Redirect to login page after successful registration
    }
}
