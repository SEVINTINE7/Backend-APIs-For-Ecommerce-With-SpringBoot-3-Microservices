package com.ecomm.identity_service.controller;


import com.ecomm.identity_service.dto.RoleDto;
import com.ecomm.identity_service.service.impl.RoleServiceImpl;
import com.ecomm.identity_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/role")
@AllArgsConstructor
public class RoleController {

    private RoleServiceImpl roleService;


    @PostMapping("/add-new-role")
    public ResponseEntity<StandardResponse> addNewRole(@RequestBody RoleDto roleDto) {
        RoleDto savedRoleDto = roleService.addNewRole(roleDto);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", savedRoleDto),
                HttpStatus.CREATED
        );
    }
}
