package com.kaan.eshop.authservice.backend.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "user")
@Data
@ToString
public class User {

    @Id
    private String userId;

    private String firstname;

    private String lastname;

    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @NotNull
    private Date lastPasswordResetDate = new Date();

    public boolean isActive = true;
    
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
        roles.add(new Role());
    }

    public User(String firstname, String lastname, String password, String email, Set<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public User(User user) {
        this.isActive = user.isActive();
        this.userId = user.getUserId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

    public void addRole(Role role) {
        this.getRoles().add(role);
    }

}
