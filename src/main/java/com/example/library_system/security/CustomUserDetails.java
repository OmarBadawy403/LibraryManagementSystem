package com.example.library_system.security;

import com.example.library_system.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert user role to Spring Security authority with "ROLE_" prefix
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public String getPassword() {
        // Return user's password
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // Return username
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Account is never expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Account is never locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Credentials never expire
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Return if user is enabled or not
        return user.isEnabled();
    }
}
