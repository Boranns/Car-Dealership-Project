package com.borangalleri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.borangalleri.model.Address;

@Repository
public interface AdressRepository extends JpaRepository<Address, Long>{

}
