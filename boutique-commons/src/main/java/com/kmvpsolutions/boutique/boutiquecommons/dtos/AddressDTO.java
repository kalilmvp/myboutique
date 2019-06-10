package com.kmvpsolutions.boutique.boutiquecommons.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {
    private String address1;
    private String address2;
    private String city;
    private String postcode;
    private String country;
}
