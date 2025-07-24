package com.ecomm.identity_service.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressUpdateRequest {
    private Long addressId;

    private String addressNo;

    private String city;

    private String state;

    private String street;

    private String country;
}
