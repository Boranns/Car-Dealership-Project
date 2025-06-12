package com.borangalleri.service;

import com.borangalleri.dto.DtoAccount;
import com.borangalleri.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

}
