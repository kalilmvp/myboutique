package com.kmvpsolutions.ao.boutiquespringboot.repository;

import com.kmvpsolutions.ao.boutiquespringboot.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> { }
