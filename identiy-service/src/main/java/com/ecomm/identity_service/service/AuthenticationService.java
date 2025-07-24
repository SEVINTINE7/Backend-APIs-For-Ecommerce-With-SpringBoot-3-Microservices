package com.ecomm.identity_service.service;

import com.ecomm.identity_service.dto.requests.AuthRequest;
import com.ecomm.identity_service.dto.requests.CustomerRegRequest;
import com.ecomm.identity_service.dto.responses.CustomerRegResponse;
import com.ecomm.identity_service.entity.User;

public interface AuthenticationService {

    CustomerRegResponse cSignUp(CustomerRegRequest request);

    User cSignIn(AuthRequest request);

    CustomerRegResponse aSignUp(CustomerRegRequest request);

    User aSignIn(AuthRequest request);

    CustomerRegResponse staffSignUp(CustomerRegRequest request);

    User staffSignIn(AuthRequest request);
}
