package com.ecomm.identity_service.controller;

import com.ecomm.identity_service.config.JwtUtilImpl;
import com.ecomm.identity_service.dto.requests.AuthRequest;
import com.ecomm.identity_service.dto.requests.CustomerRegRequest;
import com.ecomm.identity_service.dto.responses.AuthResponse;
import com.ecomm.identity_service.dto.responses.CustomerRegResponse;
import com.ecomm.identity_service.entity.User;
import com.ecomm.identity_service.service.impl.AuthenticationServiceImpl;
import com.ecomm.identity_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
@AllArgsConstructor
public class AdminController {

    private AuthenticationServiceImpl service;
    private JwtUtilImpl jwtService;

    @PostMapping("/register")
    public ResponseEntity<StandardResponse> adminSignUp(@RequestBody CustomerRegRequest request) {
        CustomerRegResponse response = service.aSignUp(request);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", response),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<StandardResponse> adminSignIn(@RequestBody AuthRequest request) {
        User authedUser = service.aSignIn(request);
        String token = jwtService.generateToken(authedUser);
        AuthResponse response = new AuthResponse(
                token,
                jwtService.getExpirationTime(token)
        );
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", response),
                HttpStatus.ACCEPTED
        );
    }
}
