package com.kmvpsolutions.boutique.productservice.services;

import com.kmvpsolutions.boutique.boutiquecommons.dtos.CategoryDTO;
import com.kmvpsolutions.boutique.productservice.entities.Category;
import com.kmvpsolutions.boutique.productservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> findAll() {
        log.info("Request find all categories");
        return this.categoryRepository.findAll()
                .stream()
                .map(CategoryService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        log.info("Request find one category");
        return this.categoryRepository.findById(id)
                .map(CategoryService::mapToDTO)
                .orElseThrow(IllegalStateException::new);
    }

    public CategoryDTO create(CategoryDTO categoryDTO) {
        log.info("Request create category");
        return mapToDTO(this.categoryRepository.save(new Category(
            categoryDTO.getName(),
            categoryDTO.getDescription(),
            Collections.emptySet()
        )));
    }

    public void delete(Long id) {
        log.info("Request delete category");
        this.categoryRepository.deleteById(id);
    }

    public static CategoryDTO mapToDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                Collections.emptySet());
    }
}
