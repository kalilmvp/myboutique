package com.kmvpsolutions.ao.boutiquespringboot.repository;

import com.kmvpsolutions.ao.boutiquespringboot.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> { }
