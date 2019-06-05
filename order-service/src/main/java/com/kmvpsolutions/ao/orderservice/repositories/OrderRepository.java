package com.kmvpsolutions.ao.orderservice.repositories;

import com.kmvpsolutions.ao.orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCartId(Long id);
}
