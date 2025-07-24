package com.ecomm.identity_service.repository;

import com.ecomm.identity_service.entity.Address;
import com.ecomm.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AddressRepo extends JpaRepository<Address, Long> {
    List<Address> findAllByUser(User user);
}
