package com.ecomm.identity_service.service.impl;

import com.ecomm.identity_service.dto.RoleDto;
import com.ecomm.identity_service.entity.Role;
import com.ecomm.identity_service.repository.RoleRepo;
import com.ecomm.identity_service.service.RoleService;
import com.ecomm.identity_service.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepo roleRepo;
    private Mapper mapper;

    @Override
    public RoleDto addNewRole(RoleDto roleDto) {
        Role role = roleRepo.save(mapper.mapRoleDtoToRole(roleDto));
        return mapper.mapRoleToRoleDto(role);
    }
}
