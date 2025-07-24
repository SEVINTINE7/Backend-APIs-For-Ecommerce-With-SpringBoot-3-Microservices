package com.ecomm.identity_service.config;


import com.ecomm.identity_service.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtUtil {
    String generateToken(User user);
    String extractUsername(String token);
    Date getExpirationTime(String token);
    boolean validateToken(String token, String user);
}
