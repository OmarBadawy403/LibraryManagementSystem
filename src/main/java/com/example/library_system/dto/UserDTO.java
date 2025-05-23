package com.example.library_system.dto;

// Data Transfer Object for user details
public class UserDTO {

    private int id;
    private String username;
    private String role;

    // Default constructor
    public UserDTO() {}

    // Constructor with all fields
    public UserDTO(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
