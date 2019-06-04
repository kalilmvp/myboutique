package com.kmvpsolutions.ao.customerservice.resources;

import com.kmvpsolutions.ao.boutiquecommons.dtos.CustomerDTO;
import com.kmvpsolutions.ao.boutiquecommons.utilities.Web;
import com.kmvpsolutions.ao.customerservice.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Web.API + "/customers")
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
