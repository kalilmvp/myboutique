package com.kmvpsolutions.ao.boutiquespringboot.services;


import com.kmvpsolutions.ao.boutiquespringboot.entities.Order;
import com.kmvpsolutions.ao.boutiquespringboot.entities.Payment;
import com.kmvpsolutions.ao.boutiquespringboot.commons.dtos.PaymentDTO;
import com.kmvpsolutions.ao.boutiquespringboot.enums.PaymentStatus;
import com.kmvpsolutions.ao.boutiquespringboot.repository.OrderRepository;
import com.kmvpsolutions.ao.boutiquespringboot.repository.PaymentRepository;
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
        log.debug("Find all payments");
        return this.paymentRepository.findAll()
                .stream()
                .map(PaymentService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PaymentDTO findById(Long id) {
        log.debug("Find a payment by id {}", id);
        return this.paymentRepository.findById(id).map(PaymentService::mapToDTO)
            .orElseThrow(() -> new IllegalStateException("No payment found"));
    }

    public PaymentDTO create(PaymentDTO paymentDTO) {
        log.debug("Create a payment {}", paymentDTO);
        Order order = this.orderRepository.findById(paymentDTO.getOrderId()).orElseThrow(() ->
                new IllegalStateException("The order does not exist!"));
        return mapToDTO(this.paymentRepository.save(new Payment(
                paymentDTO.getPaypalPaymentId(),
                PaymentStatus.valueOf(paymentDTO.getStatus()),
                order
        )));
    }

    public void delete(Long id) {
        log.debug("Request delete payment id: {}", id);
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
