package com.kmvpsolutions.ao.boutiquespringboot.resources;

import com.kmvpsolutions.ao.boutiquespringboot.commons.dtos.OrderItemDTO;
import com.kmvpsolutions.ao.boutiquespringboot.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kmvpsolutions.ao.boutiquespringboot.commons.utilities.Web.API;

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
    public OrderItemDTO create(OrderItemDTO orderItemDTO) {
        return this.orderItemService.create(orderItemDTO);
    }
}
