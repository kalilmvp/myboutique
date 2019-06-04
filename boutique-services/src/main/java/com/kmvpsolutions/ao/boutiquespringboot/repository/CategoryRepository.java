package com.kmvpsolutions.ao.boutiquespringboot.repository;

import com.kmvpsolutions.ao.boutiquespringboot.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> { }
