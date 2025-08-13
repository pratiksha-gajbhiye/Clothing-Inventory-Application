package com.BillingApp.controller;

import com.BillingApp.DTO.CustomerDTO;
import com.BillingApp.model.Customer;
import com.BillingApp.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	
	@Autowired
    private CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody CustomerDTO dto) {
        return customerService.createCustomer(dto);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }

    @PostMapping("/import")
    public ResponseEntity<String> importCustomers(@RequestBody List<CustomerDTO> dtos) {
        customerService.importCustomers(dtos);
        return ResponseEntity.ok("Imported Successfully");
    }

    @GetMapping("/due-bills")
    public List<Customer> getCustomersWithDueBills() {
        return customerService.getCustomersWithDueBills();
    }
}
