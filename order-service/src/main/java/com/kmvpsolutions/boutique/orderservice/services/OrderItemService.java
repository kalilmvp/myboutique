package com.kmvpsolutions.boutique.orderservice.services;

import com.kmvpsolutions.boutique.boutiquecommons.dtos.OrderItemDTO;
import com.kmvpsolutions.boutique.boutiquecommons.dtos.ProductDTO;
import com.kmvpsolutions.boutique.orderservice.client.ProductServiceClient;
import com.kmvpsolutions.boutique.orderservice.entities.Order;
import com.kmvpsolutions.boutique.orderservice.entities.OrderItem;
import com.kmvpsolutions.boutique.orderservice.repositories.OrderItemRepository;
import com.kmvpsolutions.boutique.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;

    public List<OrderItemDTO> findAll() {
        log.info("Request to get all order items");
        return this.orderItemRepository.findAll()
                .stream()
                .map(OrderItemService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderItemDTO findById(Long id) {
        log.info("Request find one order item");
        return this.orderItemRepository.findById(id)
                .map(OrderItemService::mapToDTO)
                .orElseThrow(IllegalStateException::new);
    }

    public OrderItemDTO create(OrderItemDTO orderItemDTO) {
        log.info("Request to create an order item: {}", orderItemDTO);
        Order order = this.orderRepository.findById(orderItemDTO.getOrderId()).orElseThrow(() ->
                new IllegalStateException("The order does not exist!"));

        ProductDTO productDTO = this.productServiceClient.getProductById(orderItemDTO.getProductId()).orElseThrow(() ->
                new IllegalStateException("The product does not exist!"));

        return mapToDTO(this.orderItemRepository.save(new OrderItem(
                orderItemDTO.getQuantity(),
                productDTO.getId(),
                order
        )));
    }

    public void delete(Long id) {
        log.info("Request delete order item: {}", id);
        this.orderItemRepository.deleteById(id);
    }

    public static OrderItemDTO mapToDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getProductId(),
                orderItem.getOrder().getId()
        );
    }
}
