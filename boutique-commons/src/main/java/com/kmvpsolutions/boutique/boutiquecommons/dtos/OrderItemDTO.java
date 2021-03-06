package com.kmvpsolutions.boutique.boutiquecommons.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Long quantity;
    private Long productId;
    private Long orderId;
}
