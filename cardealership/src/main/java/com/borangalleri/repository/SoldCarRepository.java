package com.borangalleri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.borangalleri.model.SoldCar;

@Repository
public interface SoldCarRepository extends JpaRepository<SoldCar, Long>{

}
