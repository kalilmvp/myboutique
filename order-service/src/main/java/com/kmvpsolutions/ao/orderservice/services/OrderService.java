package com.kmvpsolutions.ao.orderservice.services;

import com.kmvpsolutions.ao.boutiquespringboot.entities.Cart;
import com.kmvpsolutions.ao.boutiquespringboot.entities.Order;
import com.kmvpsolutions.ao.boutiquecommons.dtos.OrderDTO;
import com.kmvpsolutions.ao.boutiquespringboot.enums.OrderStatus;
import com.kmvpsolutions.ao.boutiquespringboot.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDTO> findAll() {
        log.debug("Find all orders");
        return this.orderRepository.findAll()
                .stream()
                .map(OrderService::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> findAllByUser(Long customerId) {
        log.debug("Find all orders by user");
        return this.orderRepository.findByCartId(customerId)
                .stream()
                .map(OrderService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        log.debug("Request find one order");
        return this.orderRepository.findById(id)
                .map(OrderService::mapToDTO)
                .orElseThrow(IllegalStateException::new);
    }

    public OrderDTO create(OrderDTO orderDTO) {
        log.debug("Create a order {}", orderDTO);

        return mapToDTO(this.orderRepository.save(new Order(
                BigDecimal.ZERO,
                OrderStatus.CREATION,
                null,
                null,
                null,
                Collections.emptySet(),
                null
        )));
    }

    public Order create(Cart cart) {
        log.debug("Create a order with a cart {}", cart);

        return this.orderRepository.save(new Order(
                BigDecimal.ZERO,
                OrderStatus.CREATION,
                null,
                null,
                null,
                Collections.emptySet(),
                cart.getId()
        ));
    }

    public void delete(Long id) {
        log.debug("Request delete order: {}", id);
        this.orderRepository.deleteById(id);
    }

    private static OrderDTO mapToDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getTotalPrice(),
                order.getStatus().name(),
                order.getShipped(),
                PaymentService.mapToDTO(order.getPayment()),
                AddressService.mapToDTO(order.getAddress()),
                order.getItems().stream().map(OrderItemService::mapToDTO).collect(Collectors.toSet())
        );
    }
}
