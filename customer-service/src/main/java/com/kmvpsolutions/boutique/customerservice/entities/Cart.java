package com.kmvpsolutions.boutique.customerservice.entities;

import com.kmvpsolutions.boutique.boutiquecommons.domain.AbstractEntity;
import com.kmvpsolutions.boutique.customerservice.enums.CartStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "cart")
public class Cart extends AbstractEntity {

    private Long orderId;

    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    public Cart() {}

    public void defineOrder(Long orderId) {
        this.orderId = orderId;
    }

    public Cart(Customer customer) {
        this.customer = customer;
        this.status = CartStatus.NEW;
    }
}
