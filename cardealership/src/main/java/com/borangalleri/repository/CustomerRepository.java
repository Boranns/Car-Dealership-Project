package com.borangalleri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.borangalleri.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
