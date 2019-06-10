package com.kmvpsolutions.boutique.orderservice.repositories;

import com.kmvpsolutions.boutique.orderservice.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {}
