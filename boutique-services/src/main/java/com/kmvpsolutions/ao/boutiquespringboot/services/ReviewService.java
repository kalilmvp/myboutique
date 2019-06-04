package com.kmvpsolutions.ao.boutiquespringboot.services;

import com.kmvpsolutions.ao.boutiquespringboot.entities.Review;
import com.kmvpsolutions.ao.boutiquespringboot.commons.dtos.ReviewDTO;
import com.kmvpsolutions.ao.boutiquespringboot.repository.ReviewRepository;
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
        log.debug("Get all reviews");
        return this.reviewRepository.findAll()
                .stream()
                .map(ReviewService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id) {
        log.debug("Find a review by id {}", id);
        return this.reviewRepository.findById(id).map(ReviewService::mapToDTO)
                .orElseThrow(() -> new IllegalStateException("No review  found"));
    }

    public ReviewDTO create(ReviewDTO review) {
        log.debug("Create a review {}", review);
        return mapToDTO(
                this.reviewRepository.save(new Review(
                        review.getTitle(),
                        review.getDescription(),
                        review.getRating()
                ))
        );
    }

    public void delete(Long id) {
        log.debug("Request delete review id: {}", id);
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
