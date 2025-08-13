package com.example.CRM.service;

import com.example.CRM.model.Customer;
import com.example.CRM.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.CRM.model.Customer;
@Service
public class CustomerService {


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private final CustomerRepository customerRepository;


    public List<Customer> getAllCustomer(){
        return (List<Customer>) customerRepository.findAll();
    }

    public Optional<Customer>findById(Long id){
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }


    public Customer getCustomerById(Long userId) {
        return customerRepository.findById(userId).orElse(null);
    }


}
