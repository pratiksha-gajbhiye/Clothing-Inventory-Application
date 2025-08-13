package com.BillingApp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BillingApp.model.Customer;
import com.BillingApp.model.OpeningBalancePayment;
import com.BillingApp.repository.CustomerRepository;
import com.BillingApp.services.CustomerService;
import com.BillingApp.DTO.CustomerDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired private CustomerRepository customerRepo;

    @Override
    public Customer createCustomer(CustomerDTO dto) {
        if (customerRepo.existsByMobile(dto.getMobile())) {
            throw new RuntimeException("Customer already exists with mobile: " + dto.getMobile());
        }

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setMobile(dto.getMobile());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setGstNumber(dto.getGstNumber());
        customer.setTaxNumber(dto.getTaxNumber());
        customer.setCountry(dto.getCountry());
        customer.setState(dto.getState());
        customer.setCity(dto.getCity());
        customer.setPostcode(dto.getPostcode());
        customer.setAddress(dto.getAddress());
        customer.setOpeningBalancePayments(dto.getOpeningBalance());

        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public void importCustomers(List<CustomerDTO> dtos) {
        List<Customer> customers = dtos.stream().map(dto -> {
            Customer c = new Customer();
            c.setName(dto.getName());
            c.setMobile(dto.getMobile());
            c.setEmail(dto.getEmail());
            c.setPhone(dto.getPhone());
            c.setGstNumber(dto.getGstNumber());
            c.setTaxNumber(dto.getTaxNumber());
            c.setCountry(dto.getCountry());
            c.setState(dto.getState());
            c.setCity(dto.getCity());
            c.setPostcode(dto.getPostcode());
            c.setAddress(dto.getAddress());
            c.setOpeningBalancePayments((List<OpeningBalancePayment>) dto.getOpeningBalance());
            return c;
        }).collect(Collectors.toList());

        customerRepo.saveAll(customers);
    }

    @Override
    public List<Customer> getCustomersWithDueBills() {
        // Example with filtering logic
        return customerRepo.findAll().stream()
                .filter(c -> c.getOpeningBalancePayments() != null && ((BigDecimal) c.getOpeningBalancePayments()).compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());
    }
}
