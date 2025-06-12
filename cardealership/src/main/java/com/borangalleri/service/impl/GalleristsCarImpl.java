package com.borangalleri.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borangalleri.dto.DtoAdress;
import com.borangalleri.dto.DtoCar;
import com.borangalleri.dto.DtoGallerist;
import com.borangalleri.dto.DtoGalleristsCar;
import com.borangalleri.dto.DtoGalleristsCarIU;
import com.borangalleri.exception.BaseException;
import com.borangalleri.exception.ErrorMessage;
import com.borangalleri.exception.MessageType;
import com.borangalleri.model.Car;
import com.borangalleri.model.Gallerist;
import com.borangalleri.model.GalleristsCar;
import com.borangalleri.repository.CarRepository;
import com.borangalleri.repository.GalleristRepository;
import com.borangalleri.repository.GalleristsCarRepository;
import com.borangalleri.service.IGalleristsCarService;

@Service
public class GalleristsCarImpl implements IGalleristsCarService{

    @Autowired
    private GalleristsCarRepository galleristsCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    private GalleristsCar createGalleristCar(DtoGalleristsCarIU dtoGalleristsCarIU){
        Optional<Gallerist> optGallerist = 
         galleristRepository.findById(dtoGalleristsCarIU.getGalleristID());
         if (optGallerist.isEmpty()) {
            throw new BaseException(
         new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristsCarIU.getGalleristID().toString()));
            
         }

         Optional<Car> optCar = carRepository.findById(dtoGalleristsCarIU.getCarID());
         if (optCar.isEmpty()) {
            throw new BaseException(
                new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristsCarIU.getCarID().toString()));
         }
        
        GalleristsCar galleristsCar = new GalleristsCar();
        galleristsCar.setCreateTime(LocalDateTime.now());
        galleristsCar.setGallerist(optGallerist.get());
        galleristsCar.setCar(optCar.get());

        return galleristsCar;
    }


    @Override
    public DtoGalleristsCar saveCar (DtoGalleristsCarIU dtoGalleristsCarIU) {
        DtoGalleristsCar dtoGalleristsCar = new DtoGalleristsCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoAdress dtoAddress = new DtoAdress();

        GalleristsCar savedGalleristsCar = 
        galleristsCarRepository.save(createGalleristCar(dtoGalleristsCarIU));

        BeanUtils.copyProperties(savedGalleristsCar, dtoGalleristsCar);
        BeanUtils.copyProperties(savedGalleristsCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(savedGalleristsCar.getGallerist().getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedGalleristsCar.getCar(), dtoCar);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristsCar.setGallerist(dtoGallerist);
        dtoGalleristsCar.setCar(dtoCar);


        
        return dtoGalleristsCar;
    }

}
