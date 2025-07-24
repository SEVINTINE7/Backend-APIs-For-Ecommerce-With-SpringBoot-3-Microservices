package com.ecomm.identity_service.repository;

import com.ecomm.identity_service.entity.Role;
import com.ecomm.identity_service.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RoleRepo extends JpaRepository<Role, RoleEnum> {
}
