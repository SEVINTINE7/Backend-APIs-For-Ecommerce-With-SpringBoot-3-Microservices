package com.ecomm.identity_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long addressId;

    private String addressNo;

    private String city;

    private String state;

    private String street;

    private String country;

    private String user;
}
