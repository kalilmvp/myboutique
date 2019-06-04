package com.kmvpsolutions.ao.boutiquespringboot.resources;

import static com.kmvpsolutions.ao.boutiquespringboot.commons.utilities.Web.*;

import com.kmvpsolutions.ao.boutiquespringboot.commons.dtos.CartDTO;
import com.kmvpsolutions.ao.boutiquespringboot.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/carts")
public class CartResource {

    private final CartService cartService;

    @GetMapping
    public List<CartDTO> listAll() {
        return this.cartService.findAll();
    }

    @GetMapping("/active")
    public List<CartDTO> findAllActiveCarts() {
        return this.cartService.findAllActiveCarts();
    }

    @GetMapping("/customer/{id}")
    public CartDTO getActiveCartForCustomer(@PathVariable("id") Long customerId) {
        return this.cartService.getActiveCart(customerId);
    }

    @GetMapping("/{id}")
    public CartDTO findById(@PathVariable Long id) {
        return this.cartService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.cartService.delete(id);
    }

    @PostMapping("/customer/{id}")
    public CartDTO create(@PathVariable("id") Long id) {
        return this.cartService.create(id);
    }
}
