package com.example.CRM.service;

import com.example.CRM.model.Customer;
import com.example.CRM.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CRM.model.Customer;
import com.example.CRM.repository.CustomerRepository;
import org.springframework.stereotype.Service;
@Service  // marks this class as a Spring-managed Service component
public class CustomerServiceImpl implements CustomerService1 {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
