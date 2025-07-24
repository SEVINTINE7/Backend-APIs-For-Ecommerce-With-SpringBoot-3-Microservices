package com.ecomm.identity_service.dto;

import com.ecomm.identity_service.entity.Role;
import com.ecomm.identity_service.entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;

    private String password;

    private String email;

    private String fName;

    private String lName;

    private RoleEnum role;
}
