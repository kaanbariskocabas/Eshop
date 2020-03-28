package com.kaan.eshop.authservice.app.datagenerator;

import com.kaan.eshop.authservice.backend.model.Role;
import com.kaan.eshop.authservice.backend.model.User;
import com.kaan.eshop.authservice.backend.model.type.RoleType;
import com.kaan.eshop.authservice.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Kaan Kocabas
 */
@Component
public class DataGenerator {

    private List<User> users = new ArrayList<>();
    private Set<Role> roles = new HashSet<>();

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        return args -> {
            if (hasData(userRepository)) {
                System.out.println("Using existing database");
                return;
            }
            System.out.println("... generating roles");
            createRoles();
            System.out.println("... generating users");
            createUsers(userRepository, bCryptPasswordEncoder);
            System.out.println("... generating todos");
        };
    }

    private void createRoles() {
        roles.add(new Role(RoleType.ROLE_USER));
        roles.add(new Role(RoleType.ROLE_ADMIN));
    }

    private void createUsers(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        users.add(userRepository
                .save(new User("admin", "allmighty", bCryptPasswordEncoder.encode("admin"),
                        "admin@admin.com", roles)));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(RoleType.ROLE_USER));
        User user2 = new User();
        user2.setEmail("user@user.com");
        user2.setRoles(roleSet);
        user2.setPassword(bCryptPasswordEncoder.encode("user"));
        users.add(userRepository.save(user2));
    }

    private boolean hasData(UserRepository userRepository) {
        return userRepository.count() != 0L;
    }

}
