package com.kaan.eshop.authservice.app.config;

import com.kaan.eshop.authservice.backend.model.Role;
import com.kaan.eshop.authservice.backend.model.User;
import com.kaan.eshop.authservice.backend.model.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static UserDTO create(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles()),
                user.isActive(),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());
    }
}
