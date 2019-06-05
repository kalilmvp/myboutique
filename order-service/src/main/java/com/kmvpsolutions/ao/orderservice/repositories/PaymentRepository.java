package com.kmvpsolutions.ao.orderservice.repositories;

import com.kmvpsolutions.ao.orderservice.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {}
