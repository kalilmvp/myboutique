package com.kmvpsolutions.boutique.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kmvpsolutions.boutique.boutiquecommons.domain.AbstractEntity;
import com.kmvpsolutions.boutique.orderservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    @NotNull
    @Column(name = "total_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalPrice;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;
    private ZonedDateTime shipped;
    @OneToOne
    @JoinColumn(unique = true)
    private Payment payment;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<OrderItem> items = new HashSet<>();
    private Long cartId;

}
