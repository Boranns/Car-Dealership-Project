package com.borangalleri.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borangalleri.controller.IRestAddressController;
import com.borangalleri.controller.RestBaseController;
import com.borangalleri.controller.RootEntity;
import com.borangalleri.dto.DtoAdress;
import com.borangalleri.dto.DtoAdressIU;
import com.borangalleri.service.IAdressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController{

    @Autowired
    private IAdressService addressService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoAdress> saveAddress(@Valid @RequestBody DtoAdressIU dtoAddressIU) {
        
        return ok(addressService.saveAdress(dtoAddressIU));
    }

}
