package com.kmvpsolutions.boutique.boutiquecommons.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentDTO {
    private Long id;
    private String paypalPaymentId;
    private String status;
    private Long orderId;
}
