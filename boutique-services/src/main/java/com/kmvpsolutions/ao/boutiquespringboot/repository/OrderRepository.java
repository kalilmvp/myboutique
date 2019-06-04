package com.kmvpsolutions.ao.boutiquespringboot.repository;

import com.kmvpsolutions.ao.boutiquespringboot.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCartId(Long id);
}
