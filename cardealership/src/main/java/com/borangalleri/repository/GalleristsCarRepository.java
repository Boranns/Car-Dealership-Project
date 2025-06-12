package com.borangalleri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.borangalleri.model.GalleristsCar;

@Repository
public interface GalleristsCarRepository extends JpaRepository<GalleristsCar, Long>{

}
