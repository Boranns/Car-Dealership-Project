package com.borangalleri.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borangalleri.controller.IRestAccountController;
import com.borangalleri.controller.RestBaseController;
import com.borangalleri.controller.RootEntity;
import com.borangalleri.dto.DtoAccount;
import com.borangalleri.dto.DtoAccountIU;
import com.borangalleri.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController{

    @Autowired
    private IAccountService accountService;

    @RequestMapping("/save")
    @Override
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        
        return ok(accountService.saveAccount(dtoAccountIU));
    }

}
