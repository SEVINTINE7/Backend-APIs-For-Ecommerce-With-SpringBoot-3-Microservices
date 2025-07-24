package com.ecomm.identity_service.dto.responses;


import com.ecomm.identity_service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateResponse {
    private Long addressId;

    private String addressNo;

    private String city;

    private String state;

    private String street;

    private String country;

    private String user;
}
