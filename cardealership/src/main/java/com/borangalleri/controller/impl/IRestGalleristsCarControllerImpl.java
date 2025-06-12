package com.borangalleri.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borangalleri.controller.IRestGalleristsController;
import com.borangalleri.controller.RestBaseController;
import com.borangalleri.controller.RootEntity;
import com.borangalleri.dto.DtoGalleristsCar;
import com.borangalleri.dto.DtoGalleristsCarIU;
import com.borangalleri.service.IGalleristsCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerists-car")
public class IRestGalleristsCarControllerImpl extends RestBaseController implements IRestGalleristsController {

    @Autowired
    private IGalleristsCarService galleristsCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGalleristsCar> saveGalleristsCar(@Valid @RequestBody DtoGalleristsCarIU galleristCarIU) {
        
        return ok(galleristsCarService.saveCar(galleristCarIU));
    }

   

}
