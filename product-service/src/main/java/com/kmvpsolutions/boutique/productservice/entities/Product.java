package com.kmvpsolutions.boutique.productservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kmvpsolutions.boutique.boutiquecommons.domain.AbstractEntity;
import com.kmvpsolutions.boutique.productservice.enumms.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "product")
public class Product extends AbstractEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;
    @NotNull
    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;
    @Column(name = "quantity")
    private Integer quantity;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status;
    @Column(name = "sales_counter")
    private Integer salesCounter;
    @OneToMany
    @JsonIgnore
    private Set<Review> reviews = new HashSet<>();
    @ManyToOne
    private Category category;
}
