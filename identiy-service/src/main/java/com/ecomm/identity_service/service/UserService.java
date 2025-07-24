package com.ecomm.identity_service.service;

import com.ecomm.identity_service.dto.AddressDto;
import com.ecomm.identity_service.dto.requests.CustomerRegRequest;
import com.ecomm.identity_service.dto.responses.CustomerRegResponse;

import java.util.List;

public interface UserService {
    AddressDto addNewAddress(AddressDto addressDto);

    CustomerRegResponse updateProfile(CustomerRegRequest regRequest);

    List<AddressDto> getAllAddresses(String username);

    AddressDto updateAddressById(Long adId, AddressDto addressDto);

    String deleteAddressById(Long adId);

    Boolean validateTokenWithUsername(String token);
}
