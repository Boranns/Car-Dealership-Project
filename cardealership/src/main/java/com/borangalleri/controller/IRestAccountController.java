package com.borangalleri.controller;

import com.borangalleri.dto.DtoAccount;
import com.borangalleri.dto.DtoAccountIU;

public interface IRestAccountController {

    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);

}
