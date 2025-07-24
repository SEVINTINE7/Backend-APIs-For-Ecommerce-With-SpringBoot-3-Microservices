package com.ecomm.identity_service.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegRequest {
    private String username;

    private String password;

    private String email;

    private String fName;

    private String lName;
}
