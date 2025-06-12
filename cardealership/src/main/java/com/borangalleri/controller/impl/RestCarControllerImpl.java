package com.borangalleri.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borangalleri.controller.IRestCarController;
import com.borangalleri.controller.RestBaseController;
import com.borangalleri.controller.RootEntity;
import com.borangalleri.dto.DtoCar;
import com.borangalleri.dto.DtoCarIU;
import com.borangalleri.service.ICarService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController{

    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
    
        return ok(carService.saveCar(dtoCarIU));
    }

}
