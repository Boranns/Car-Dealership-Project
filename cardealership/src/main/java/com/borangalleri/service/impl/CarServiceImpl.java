package com.borangalleri.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.borangalleri.dto.DtoCar;
import com.borangalleri.dto.DtoCarIU;
import com.borangalleri.model.Car;
import com.borangalleri.repository.CarRepository;
import com.borangalleri.service.ICarService;

public class CarServiceImpl implements ICarService{

    @Autowired
    private CarRepository carRepository;

    
    private Car createCar(DtoCarIU dtoCarIU){
        Car car = new Car();
        car.setCreateTime(LocalDateTime.now());
        BeanUtils.copyProperties(car, dtoCarIU);
    

        return car;
    }
    
    
    
    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {
        DtoCar dtoCar = new DtoCar();
        Car savedCar = carRepository.save(createCar(dtoCarIU));

        BeanUtils.copyProperties(savedCar, dtoCar);
        
        
        return dtoCar;
    }

}
