package com.kmvpsolutions.ao.boutiquecommons.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDTO {

    private Long id;
    private Long orderId;
    private CustomerDTO customerDTO;
    private String status;
}
