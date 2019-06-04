package com.kmvpsolutions.ao.boutiquespringboot.commons.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String status;
    private Integer salesCounter;
    private Set<ReviewDTO> reviews;
    private CategoryDTO category;
}
