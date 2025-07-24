package com.ecomm.identity_service.service.impl;

import com.ecomm.identity_service.config.JwtUtil;
import com.ecomm.identity_service.dto.AddressDto;
import com.ecomm.identity_service.dto.requests.CustomerRegRequest;
import com.ecomm.identity_service.dto.responses.CustomerRegResponse;
import com.ecomm.identity_service.entity.Address;
import com.ecomm.identity_service.entity.User;
import com.ecomm.identity_service.repository.AddressRepo;
import com.ecomm.identity_service.repository.UserRepo;
import com.ecomm.identity_service.service.UserService;
import com.ecomm.identity_service.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private AddressRepo addressRepo;
    private UserRepo userRepo;
    private Mapper mapper;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private CustomUserDetailsService userDetailSerivce;

    @Override
    public AddressDto addNewAddress(AddressDto addressDto) {
        User user = userRepo.findUserByUsername(addressDto.getUser());
        Address address = mapper.mapAddressDtoToAddress(addressDto, user);
        Address savedAddress = addressRepo.save(address);
        return mapper.mapAddressToAddressDto(savedAddress);
    }

    @Override
    public CustomerRegResponse updateProfile(CustomerRegRequest request) {
        if (userRepo.existsById(request.getUsername())) {
            User existingUser = userRepo.getReferenceById(request.getUsername());
            existingUser.setEmail(request.getEmail());
            existingUser.setFName(request.getFName());
            existingUser.setLName(request.getLName());
            existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
            User updatedUser = userRepo.save(existingUser);
            return mapper.mapUserToCus(updatedUser);
        }
        throw new UsernameNotFoundException("No user found - " + request.getUsername());
    }

    @Override
    public List<AddressDto> getAllAddresses(String username) {
        if (userRepo.existsById(username)) {
            List<Address> addressList = addressRepo.findAllByUser(userRepo.getReferenceById(username));
            return mapper.mapAddressListToAddressDtoList(addressList);
        }
        throw new UsernameNotFoundException("No user found");
    }

    @Override
    public AddressDto updateAddressById(Long adId, AddressDto addressDto) {
        if (addressRepo.existsById(adId)) {
            Address existingAddress = addressRepo.getReferenceById(adId);
            existingAddress.setAddressNo(addressDto.getAddressNo());
            existingAddress.setCity(addressDto.getCity());
            existingAddress.setCountry(addressDto.getCountry());
            existingAddress.setState(addressDto.getState());
            existingAddress.setStreet(addressDto.getStreet());
            return mapper.mapAddressToAddressDto(addressRepo.save(existingAddress));
        }
        throw new RuntimeException("No address found with " + adId);
    }

    @Override
    public String deleteAddressById(Long adId) {
        if (addressRepo.existsById(adId)) {
            addressRepo.delete(addressRepo.getReferenceById(adId));
            return "Address with " + adId + " is deleted";
        }
        throw new RuntimeException("No address found with " + adId);
    }

    @Override
    public Boolean validateTokenWithUsername(String token) {
        UserDetails userDetails = userDetailSerivce.loadUserByUsername(jwtUtil.extractUsername(token));
        return jwtUtil.validateToken(token, userDetails.getUsername());
    }
}
