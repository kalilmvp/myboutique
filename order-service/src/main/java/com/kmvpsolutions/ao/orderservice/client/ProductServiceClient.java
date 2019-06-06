package com.kmvpsolutions.ao.orderservice.client;

import com.kmvpsolutions.ao.boutiquecommons.dtos.ProductDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class ProductServiceClient {

    private final RestTemplate restTemplate;

    public ProductServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getDefaultProductById")
    public Optional<ProductDTO> getProductById(Long productId) {
        log.info("Returning product by id: {}", productId);
        ResponseEntity<ProductDTO> response =
                this.restTemplate.getForEntity(
                        "http://product-service/api/products/{id}",
                        ProductDTO.class,
                        productId);

        if (response.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(response.getBody());
        } else {
            log.error("Unable to get product with id " + productId + ". Status Code: " + response.getStatusCode());
            return Optional.empty();
        }
    }

    private Optional<ProductDTO> getDefaultProductById(Long productId) {
        log.info("Returning default product by id: {}", productId);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);
        productDTO.setName("No Product");
        return Optional.of(productDTO);
    }
}
