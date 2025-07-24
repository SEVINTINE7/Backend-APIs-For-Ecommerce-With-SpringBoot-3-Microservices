package com.ecomm.identity_service.repository;

import com.ecomm.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepo extends JpaRepository<User, String> {
    User getUserByUsername(String username);
    User findUserByUsername(String username);
}
