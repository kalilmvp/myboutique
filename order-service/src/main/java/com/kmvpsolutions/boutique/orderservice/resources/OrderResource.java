package com.kmvpsolutions.boutique.orderservice.resources;

import com.kmvpsolutions.boutique.boutiquecommons.dtos.OrderDTO;
import com.kmvpsolutions.boutique.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kmvpsolutions.boutique.boutiquecommons.utilities.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/orders")
public class OrderResource {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDTO> listAll() {
        return this.orderService.findAll();
    }

    @GetMapping("/customer/{id}")
    public List<OrderDTO> findAllByUser(@PathVariable Long id) {
        return this.orderService.findAllByUser(id);
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable Long id) {
        return this.orderService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.orderService.delete(id);
    }

    @PostMapping
    public OrderDTO create(@RequestBody OrderDTO orderItemDTO) {
        return this.orderService.create(orderItemDTO);
    }
}
