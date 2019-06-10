package com.kmvpsolutions.boutique.orderservice.repositories;

import com.kmvpsolutions.boutique.orderservice.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> { }
