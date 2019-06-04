package com.kmvpsolutions.ao.boutiquespringboot.services;

import com.kmvpsolutions.ao.boutiquespringboot.entities.Product;
import com.kmvpsolutions.ao.boutiquespringboot.commons.dtos.ProductDTO;
import com.kmvpsolutions.ao.boutiquespringboot.enums.ProductStatus;
import com.kmvpsolutions.ao.boutiquespringboot.repository.CategoryRepository;
import com.kmvpsolutions.ao.boutiquespringboot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public List<ProductDTO> findAll() {
        log.debug("Get all products");
        return this.productRepository.findAll()
                .stream()
                .map(ProductService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        log.debug("Find a product by id {}", id);
        return this.productRepository.findById(id).map(ProductService::mapToDTO)
                .orElseThrow(() -> new IllegalStateException("No product found"));
    }

    public ProductDTO create(ProductDTO product) {
        log.debug("Create a product {}", product);
        return mapToDTO(
                this.productRepository.save(new Product(
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getQuantity(),
                        ProductStatus.valueOf(product.getStatus()),
                        product.getSalesCounter(),
                        Collections.emptySet(),
                        this.categoryRepository.findById(product.getCategory().getId()).orElseThrow(() ->
                                new IllegalStateException("Category not Found!"))
                ))
        );
    }

    public void delete(Long id) {
        log.debug("Request delete product id: {}", id);
        this.productRepository.deleteById(id);
    }

    public static ProductDTO mapToDTO(Product product) {
        return new ProductDTO(
            product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getStatus().name(),
                product.getSalesCounter(),
                product.getReviews()!= null ?
                        product.getReviews().stream()
                        .map(ReviewService::mapToDTO)
                        .collect(Collectors.toSet()) : Collections.emptySet(),
                CategoryService.mapToDTO(product.getCategory())
        );
    }
}
