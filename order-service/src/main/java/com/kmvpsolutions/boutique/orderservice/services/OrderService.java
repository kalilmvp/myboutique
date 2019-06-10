package com.kmvpsolutions.boutique.orderservice.services;

import com.kmvpsolutions.boutique.boutiquecommons.dtos.CartDTO;
import com.kmvpsolutions.boutique.boutiquecommons.dtos.OrderDTO;
import com.kmvpsolutions.boutique.orderservice.entities.Order;
import com.kmvpsolutions.boutique.orderservice.enums.OrderStatus;
import com.kmvpsolutions.boutique.orderservice.repositories.OrderRepository;
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
        log.info("Find all orders");
        return this.orderRepository.findAll()
                .stream()
                .map(OrderService::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> findAllByUser(Long customerId) {
        log.info("Find all orders by user");
        return this.orderRepository.findByCartId(customerId)
                .stream()
                .map(OrderService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        log.info("Request find one order");
        return this.orderRepository.findById(id)
                .map(OrderService::mapToDTO)
                .orElseThrow(IllegalStateException::new);
    }

    public OrderDTO create(OrderDTO orderDTO) {
        log.info("Create a order {}", orderDTO);

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

    public Order create(CartDTO cart) {
        log.info("Create a order with a cart {}", cart);

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
        log.info("Request delete order: {}", id);
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
