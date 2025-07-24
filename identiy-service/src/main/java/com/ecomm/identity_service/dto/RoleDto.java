package com.ecomm.identity_service.dto;


import com.ecomm.identity_service.entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private RoleEnum roleName;

    private String roleDescription;
}
