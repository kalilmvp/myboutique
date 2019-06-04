package com.kmvpsolutions.ao.boutiquespringboot.commons.dtos;

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
