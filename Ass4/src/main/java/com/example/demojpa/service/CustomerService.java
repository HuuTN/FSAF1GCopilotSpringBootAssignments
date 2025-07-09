package com.example.demojpa.service;

import com.example.demojpa.dto.CustomerDTO;
import com.example.demojpa.entity.Customer;
import com.example.demojpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Page<CustomerDTO> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).map(this::toDTO);
    }

    public Optional<CustomerDTO> getCustomerById(Long id) {
        return customerRepository.findById(id).map(this::toDTO);
    }

    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());
        return toDTO(customerRepository.save(customer));
    }

    public Optional<CustomerDTO> updateCustomer(Long id, CustomerDTO dto) {
        return customerRepository.findById(id).map(customer -> {
            customer.setName(dto.getName());
            customer.setEmail(dto.getEmail());
            customer.setAddress(dto.getAddress());
            return toDTO(customerRepository.save(customer));
        });
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        return dto;
    }
}
