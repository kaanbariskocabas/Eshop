package com.kaan.eshop.authservice.backend.model;

import com.kaan.eshop.authservice.backend.model.type.RoleType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "role")
public class Role {

    @Id
    private String roleId;

    private RoleType role = RoleType.ROLE_USER;

    public Role() {
    }

    public Role(RoleType role) {
        this.role = role;
    }
}
