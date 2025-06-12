package com.borangalleri.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borangalleri.controller.IRestCustomerController;
import com.borangalleri.controller.RestBaseController;
import com.borangalleri.controller.RootEntity;
import com.borangalleri.dto.DtoCustomer;
import com.borangalleri.dto.DtoCustomerIU;
import com.borangalleri.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController{

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCustomer> savCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        
        return ok(customerService.saveCustomer(dtoCustomerIU));
    }

    
    

}
