package com.ecomm.identity_service.service.impl;

import com.ecomm.identity_service.entity.Address;
import com.ecomm.identity_service.entity.User;
import com.ecomm.identity_service.repository.AddressRepo;
import com.ecomm.identity_service.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;
    private AddressRepo addressRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Set<Address> addressSet = new HashSet<>(addressRepo.findAllByUser(user));

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFName(),
                user.getLName(),
                addressSet,
                user.getRole()
        );
    }
}
