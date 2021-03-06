package com.kmvpsolutions.boutique.productservice.resources;

import com.kmvpsolutions.boutique.boutiquecommons.dtos.ReviewDTO;
import com.kmvpsolutions.boutique.productservice.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kmvpsolutions.boutique.boutiquecommons.utilities.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/reviews")
public class ReviewResource {

    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDTO> listAll() {
        return this.reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ReviewDTO findById(@PathVariable Long id) {
        return this.reviewService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.reviewService.delete(id);
    }

    @PostMapping
    public ReviewDTO create(ReviewDTO reviewDTO) {
        return this.reviewService.create(reviewDTO);
    }
}
