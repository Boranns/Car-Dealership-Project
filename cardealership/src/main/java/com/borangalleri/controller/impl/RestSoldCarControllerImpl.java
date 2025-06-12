package com.borangalleri.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borangalleri.dto.DtoSoldCarIU;
import com.borangalleri.controller.IRestSoldCarController;
import com.borangalleri.controller.RestBaseController;
import com.borangalleri.controller.RootEntity;
import com.borangalleri.dto.DtoSoldCar;
import com.borangalleri.service.ISoldCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/sold-car")
public class RestSoldCarControllerImpl extends RestBaseController implements IRestSoldCarController{

    @Autowired
    private ISoldCarService soldCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoSoldCar> buyCar(@Valid @RequestBody DtoSoldCarIU dtoSoldCarIU) {
        
        
        return ok(soldCarService.buyCar(dtoSoldCarIU));
    }


}
