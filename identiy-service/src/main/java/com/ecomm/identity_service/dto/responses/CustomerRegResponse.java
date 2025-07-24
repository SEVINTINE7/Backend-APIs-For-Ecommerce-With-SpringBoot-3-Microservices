package com.ecomm.identity_service.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegResponse {
    private String username;

    private String email;

    private String fName;

    private String lName;
}
