package com.kmvpsolutions.boutique.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class Address {

    @Column(name = "address_1")
    private String address1;
    @Column(name = "address_2")
    private String address2;
    private String city;
    @NotNull
    @Size(max = 10)
    @Column(name = "postcode", length = 10)
    private String postcode;
    @NotNull
    @Size(max = 2)
    @Column(name = "country", length = 2)
    private String country;
}
