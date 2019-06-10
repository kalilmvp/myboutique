package com.kmvpsolutions.boutique.orderservice.services;


import com.kmvpsolutions.boutique.boutiquecommons.dtos.PaymentDTO;
import com.kmvpsolutions.boutique.orderservice.entities.Order;
import com.kmvpsolutions.boutique.orderservice.entities.Payment;
import com.kmvpsolutions.boutique.orderservice.enums.PaymentStatus;
import com.kmvpsolutions.boutique.orderservice.repositories.OrderRepository;
import com.kmvpsolutions.boutique.orderservice.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public List<PaymentDTO> findAll() {
        log.info("Find all payments");
        return this.paymentRepository.findAll()
                .stream()
                .map(PaymentService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PaymentDTO findById(Long id) {
        log.info("Find a payment by id {}", id);
        return this.paymentRepository.findById(id).map(PaymentService::mapToDTO)
            .orElseThrow(() -> new IllegalStateException("No payment found"));
    }

    public PaymentDTO create(PaymentDTO paymentDTO) {
        log.info("Create a payment {}", paymentDTO);
        Order order = this.orderRepository.findById(paymentDTO.getOrderId()).orElseThrow(() ->
                new IllegalStateException("The order does not exist!"));
        return mapToDTO(this.paymentRepository.save(new Payment(
                paymentDTO.getPaypalPaymentId(),
                PaymentStatus.valueOf(paymentDTO.getStatus()),
                order
        )));
    }

    public void delete(Long id) {
        log.info("Request delete payment id: {}", id);
        this.paymentRepository.deleteById(id);
    }

    public static PaymentDTO mapToDTO(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getPaypalPaymentId(),
                payment.getStatus().name(),
                payment.getOrder().getId()
        );
    }
}
