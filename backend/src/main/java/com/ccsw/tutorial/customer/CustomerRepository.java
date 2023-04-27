package com.ccsw.tutorial.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.customer.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>, JpaRepository<Customer, Long> {

    public Customer findByName(String name);
}