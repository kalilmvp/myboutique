package com.kmvpsolutions.boutique.orderservice.services;

import com.kmvpsolutions.boutique.boutiquecommons.dtos.AddressDTO;
import com.kmvpsolutions.boutique.orderservice.entities.Address;
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
