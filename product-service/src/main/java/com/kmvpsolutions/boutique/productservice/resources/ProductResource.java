package com.kmvpsolutions.boutique.productservice.resources;

import com.kmvpsolutions.boutique.boutiquecommons.dtos.ProductDTO;
import com.kmvpsolutions.boutique.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kmvpsolutions.boutique.boutiquecommons.utilities.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/products")
public class ProductResource {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> listAll() {
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.productService.delete(id);
    }

    @PostMapping
    public ProductDTO create(ProductDTO productDTO) {
        return this.productService.create(productDTO);
    }
}
