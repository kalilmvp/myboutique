package com.kmvpsolutions.ao.boutiquespringboot.services;

import com.kmvpsolutions.ao.boutiquespringboot.entities.Address;
import com.kmvpsolutions.ao.boutiquecommons.dtos.AddressDTO;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    public static AddressDTO mapToDTO(Address address) {
        return new AddressDTO(
            address.getAddress1(),
                address.getAddress2(),
                address.getCity(),
                address.getPostcode(),
                address.getCountry()
        );
    }
}
