package com.kmvpsolutions.ao.boutiquespringboot.resources;

import com.kmvpsolutions.ao.boutiquespringboot.commons.dtos.CustomerDTO;
import com.kmvpsolutions.ao.boutiquespringboot.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kmvpsolutions.ao.boutiquespringboot.commons.utilities.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/customers")
public class CustomerResource {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> listAll() {
        return this.customerService.findAll();
    }

    @PostMapping
    public CustomerDTO create(CustomerDTO customerDTO) {
        return this.customerService.create(customerDTO);
    }


    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable Long id) {
        return this.customerService.findById(id);
    }

    @GetMapping("/active")
    public List<CustomerDTO> findAllActive() {
        return this.customerService.findAllActive();
    }
    @GetMapping("/inactive")
    public List<CustomerDTO> findAllInactive() {
        return this.customerService.findAllByInactive();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.customerService.delete(id);
    }
}
