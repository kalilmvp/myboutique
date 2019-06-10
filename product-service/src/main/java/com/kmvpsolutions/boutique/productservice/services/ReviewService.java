package com.kmvpsolutions.boutique.productservice.services;

import com.kmvpsolutions.boutique.boutiquecommons.dtos.ReviewDTO;
import com.kmvpsolutions.boutique.productservice.entities.Review;
import com.kmvpsolutions.boutique.productservice.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<ReviewDTO> findAll() {
        log.info("Get all reviews");
        return this.reviewRepository.findAll()
                .stream()
                .map(ReviewService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id) {
        log.info("Find a review by id {}", id);
        return this.reviewRepository.findById(id).map(ReviewService::mapToDTO)
                .orElseThrow(() -> new IllegalStateException("No review  found"));
    }

    public ReviewDTO create(ReviewDTO review) {
        log.info("Create a review {}", review);
        return mapToDTO(
                this.reviewRepository.save(new Review(
                        review.getTitle(),
                        review.getDescription(),
                        review.getRating()
                ))
        );
    }

    public void delete(Long id) {
        log.info("Request delete review id: {}", id);
        this.reviewRepository.deleteById(id);
    }

    public static ReviewDTO mapToDTO(Review review) {
        return new ReviewDTO(
            review.getId(),
                review.getTitle(),
                review.getDescription(),
                review.getRating()
        );
    }
}
