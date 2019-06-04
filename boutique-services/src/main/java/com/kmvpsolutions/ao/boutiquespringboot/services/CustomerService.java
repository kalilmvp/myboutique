package com.kmvpsolutions.ao.boutiquespringboot.services;

import com.kmvpsolutions.ao.boutiquespringboot.entities.Customer;
import com.kmvpsolutions.ao.boutiquespringboot.commons.dtos.CustomerDTO;
import com.kmvpsolutions.ao.boutiquespringboot.repository.CustomerRepository;
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
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<CustomerDTO> findAll() {
        log.debug("Request to find all customers");
        return this.customerRepository.findAll()
                .stream()
                .map(CustomerService::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> findAllActive() {
        log.debug("Request to get all active customers");
        return this.customerRepository.findAllByEnabled(true)
                .stream()
                .map(CustomerService::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> findAllByInactive() {
        log.debug("Request to get all inactive customers");
        return this.customerRepository.findAllByEnabled(false)
                .stream()
                .map(CustomerService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerDTO findById(Long id) {
        log.debug("Request find one customer");
        return this.customerRepository.findById(id)
                .map(CustomerService::mapToDTO)
                .orElseThrow(IllegalStateException::new);
    }

    public CustomerDTO create(CustomerDTO customerDTO) {
        log.debug("Request to create Customer");
        return mapToDTO(this.customerRepository.save(new Customer(
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getEmail(),
                customerDTO.getTelephone(),
                null,
                Boolean.TRUE
        )));
    }

    public void delete(Long id) {
        log.debug("Request delete customer");
        this.customerRepository.deleteById(id);
    }

    private static CustomerDTO mapToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getTelephone()
        );
    }
}
