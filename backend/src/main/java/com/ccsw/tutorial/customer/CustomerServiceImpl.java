package com.ccsw.tutorial.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.customer.model.CustomerDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer get(Long id) {

        return this.customerRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Customer> findAll() {

        return (List<Customer>) this.customerRepository.findAll();
    }

//    public Optional<Customer> findByName(String name) {
//        return baseCustomerRepository.findByName(name);
//    }
//
//    public boolean exists(String email) {
//        return baseCustomerRepository.existsByName(name);
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, CustomerDto dto) throws Exception {

        Customer customer;

        Customer existCustomer;

        if (id == null) {
            customer = new Customer();
        } else {
            customer = this.get(id);
        }

        existCustomer = this.customerRepository.findByName(dto.getName());

        if (existCustomer == null) {

            customer.setName(dto.getName());

            this.customerRepository.save(customer);
        } else {
            throw new Exception("Name already exists");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.customerRepository.deleteById(id);
    }

}