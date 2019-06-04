package com.kmvpsolutions.ao.boutiquespringboot.entities;

import com.kmvpsolutions.ao.boutiquecommons.domain.AbstractEntity;
import com.kmvpsolutions.ao.boutiquespringboot.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "payment")
public class Payment extends AbstractEntity {

    @Column(name = "paypal_payment_id")
    private String paypalPaymentId;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;
    @OneToOne
    @JoinColumn(unique = true)
    private Order order;
}
