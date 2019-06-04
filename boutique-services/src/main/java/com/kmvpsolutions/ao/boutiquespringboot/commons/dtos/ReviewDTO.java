package com.kmvpsolutions.ao.boutiquespringboot.commons.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private String title;
    private String description;
    private Long rating;
}
