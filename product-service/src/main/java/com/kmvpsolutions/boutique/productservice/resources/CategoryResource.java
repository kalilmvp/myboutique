package com.kmvpsolutions.boutique.productservice.resources;

import com.kmvpsolutions.boutique.boutiquecommons.dtos.CategoryDTO;
import com.kmvpsolutions.boutique.productservice.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kmvpsolutions.boutique.boutiquecommons.utilities.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/categories")
public class CategoryResource {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> listAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable Long id) {
        return this.categoryService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.categoryService.delete(id);
    }

    @PostMapping
    public CategoryDTO create(CategoryDTO categoryDTO) {
        return this.categoryService.create(categoryDTO);
    }
}
