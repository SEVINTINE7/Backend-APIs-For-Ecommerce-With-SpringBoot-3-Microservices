package com.ecomm.identity_service.controller;

import com.ecomm.identity_service.dto.AddressDto;
import com.ecomm.identity_service.dto.requests.CustomerRegRequest;
import com.ecomm.identity_service.dto.responses.CustomerRegResponse;
import com.ecomm.identity_service.service.impl.UserServiceImpl;
import com.ecomm.identity_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private UserServiceImpl service;

    @GetMapping(value = "/validate")
    public ResponseEntity<Boolean> validatetokenwithusername(@RequestParam String token, @RequestParam String username){
        Boolean response = service.validateTokenWithUsername(token);
        return new ResponseEntity<>(
                response,
                HttpStatus.ACCEPTED
        );
    }


    @PostMapping("/add-address")
    public ResponseEntity<StandardResponse> addNewAddress(@RequestBody AddressDto addressDto) {
        AddressDto response = service.addNewAddress(addressDto);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", response),
                HttpStatus.OK
        );
    }

    @PutMapping("/update-profile")
    public ResponseEntity<StandardResponse> updateProfile(@RequestBody CustomerRegRequest regRequest) {
        CustomerRegResponse response = service.updateProfile(regRequest);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", response),
                HttpStatus.OK
        );
    }

    @GetMapping("/get-all-addresses")
    public ResponseEntity<StandardResponse> getAllAddresses(@RequestParam String username) {
        List<AddressDto> addressDtos = service.getAllAddresses(username);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", addressDtos),
                HttpStatus.OK
        );
    }

    @PutMapping("/update-address/{adId}")
    public ResponseEntity<StandardResponse> updateAddressById(@PathVariable Long adId, @RequestBody AddressDto addressDto) {
        AddressDto response = service.updateAddressById(adId, addressDto);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", response),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-address-by-id/{adId}")
    public ResponseEntity<StandardResponse> deleteAddressById(@PathVariable Long adId) {
        String message = service.deleteAddressById(adId);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", message),
                HttpStatus.NO_CONTENT
        );
    }
}
