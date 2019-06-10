package com.kmvpsolutions.boutique.orderservice.resources;

import com.kmvpsolutions.boutique.boutiquecommons.dtos.OrderItemDTO;
import com.kmvpsolutions.boutique.orderservice.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kmvpsolutions.boutique.boutiquecommons.utilities.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/order-items")
public class OrderItemResource {

    private final OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemDTO> listAll() {
        return this.orderItemService.findAll();
    }

    @GetMapping("/{id}")
    public OrderItemDTO findById(@PathVariable Long id) {
        return this.orderItemService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.orderItemService.delete(id);
    }

    @PostMapping
    public OrderItemDTO create(@RequestBody OrderItemDTO orderItemDTO) {
        return this.orderItemService.create(orderItemDTO);
    }
}
