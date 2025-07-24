package com.ecomm.identity_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
    @Id
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    private String roleDescription;

}
