package com.ecomm.identity_service.service.impl;

import com.ecomm.identity_service.dto.requests.AuthRequest;
import com.ecomm.identity_service.dto.requests.CustomerRegRequest;
import com.ecomm.identity_service.dto.responses.CustomerRegResponse;
import com.ecomm.identity_service.entity.Role;
import com.ecomm.identity_service.entity.RoleEnum;
import com.ecomm.identity_service.entity.User;
import com.ecomm.identity_service.repository.RoleRepo;
import com.ecomm.identity_service.repository.UserRepo;
import com.ecomm.identity_service.service.AuthenticationService;
import com.ecomm.identity_service.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepository;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Mapper mapper;

    @Override
    public CustomerRegResponse cSignUp(CustomerRegRequest request) {
        Optional<Role> optionalRole = roleRepo.findById(RoleEnum.CUSTOMER);
        if (optionalRole.isEmpty()) {
            return null;
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = mapper.mapCusToUser(request, optionalRole.get());
        User savedUser = userRepository.save(user);
        return mapper.mapUserToCus(savedUser);
    }

    @Override
    public User cSignIn(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                ));
        return userRepository.getReferenceById(request.getUsername());
    }

    @Override
    public CustomerRegResponse aSignUp(CustomerRegRequest request) {
        Optional<Role> optionalRole = roleRepo.findById(RoleEnum.ADMIN);
        if (optionalRole.isEmpty()) {
            return null;
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = mapper.mapCusToUser(request, optionalRole.get());
        User savedUser = userRepository.save(user);
        return mapper.mapUserToCus(savedUser);
    }

    @Override
    public User aSignIn(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                ));
        return userRepository.getReferenceById(request.getUsername());
    }

    @Override
    public CustomerRegResponse staffSignUp(CustomerRegRequest request) {
        Optional<Role> optionalRole = roleRepo.findById(RoleEnum.STAFF);
        if (optionalRole.isEmpty()) {
            return null;
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = mapper.mapCusToUser(request, optionalRole.get());
        User savedUser = userRepository.save(user);
        return mapper.mapUserToCus(savedUser);
    }

    @Override
    public User staffSignIn(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                ));
        return userRepository.getReferenceById(request.getUsername());
    }
}
