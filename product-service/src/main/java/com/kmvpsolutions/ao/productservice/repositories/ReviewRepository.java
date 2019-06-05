package com.kmvpsolutions.ao.productservice.repositories;

import com.kmvpsolutions.ao.productservice.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> { }