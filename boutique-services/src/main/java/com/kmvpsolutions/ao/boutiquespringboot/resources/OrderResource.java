package com.kmvpsolutions.ao.boutiquespringboot.resources;

import com.kmvpsolutions.ao.boutiquespringboot.commons.dtos.OrderDTO;
import com.kmvpsolutions.ao.boutiquespringboot.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kmvpsolutions.ao.boutiquespringboot.commons.utilities.Web.API;

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
    public OrderDTO create(OrderDTO orderItemDTO) {
        return this.orderService.create(orderItemDTO);
    }
}
