package com.ecomm.identity_service.util;


import com.ecomm.identity_service.dto.AddressDto;
import com.ecomm.identity_service.dto.requests.CustomerRegRequest;
import com.ecomm.identity_service.dto.responses.CustomerRegResponse;
import com.ecomm.identity_service.dto.RoleDto;
import com.ecomm.identity_service.entity.Address;
import com.ecomm.identity_service.entity.Role;
import com.ecomm.identity_service.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class Mapper {

    public User mapCusToUser(CustomerRegRequest request, Role role) {
        return new User(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getFName(),
                request.getLName(),
                null,
                role
        );
    }

    public CustomerRegResponse mapUserToCus(User user) {
        return new CustomerRegResponse(
                user.getUsername(),
                user.getEmail(),
                user.getFName(),
                user.getLName()
        );
    }


    public RoleDto mapRoleToRoleDto(Role role) {
        return new RoleDto(
                role.getRoleName(),
                role.getRoleDescription()
        );
    }

    public Role mapRoleDtoToRole(RoleDto roleDto) {
        return new Role(
                roleDto.getRoleName(),
                roleDto.getRoleDescription()
        );
    }

    public Address mapAddressDtoToAddress(AddressDto addressDto, User user) {
        return new Address(
                addressDto.getAddressId(),
                addressDto.getAddressNo(),
                addressDto.getCity(),
                addressDto.getState(),
                addressDto.getStreet(),
                addressDto.getCountry(),
                user
        );
    }

    public AddressDto mapAddressToAddressDto(Address ad) {
        return new AddressDto(
                ad.getAddressId(),
                ad.getAddressNo(),
                ad.getCity(),
                ad.getState(),
                ad.getStreet(),
                ad.getCountry(),
                ad.getUser().getUsername()
        );
    }

    public List<AddressDto> mapAddressListToAddressDtoList(List<Address> addressList) {
//        List<AddressDto> addressDtoList = new ArrayList<>();
//        addressList.forEach(address -> {
//            AddressDto dto = mapAddressToAddressDto(address);
//            addressDtoList.add(dto);
//        });
//        return addressDtoList;
        return addressList.stream()
                .map(this::mapAddressToAddressDto)
                .collect(Collectors.toList());
    }
}
