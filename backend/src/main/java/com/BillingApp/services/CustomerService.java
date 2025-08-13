package com.BillingApp.services;


import java.util.List;

import com.BillingApp.model.Customer;
import com.BillingApp.DTO.CustomerDTO;

public interface CustomerService {
    Customer createCustomer(CustomerDTO dto);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    void importCustomers(List<CustomerDTO> dtos);
    List<Customer> getCustomersWithDueBills(); // For future billing integration
}
