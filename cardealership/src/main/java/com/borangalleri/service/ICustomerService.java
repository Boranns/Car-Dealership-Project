package com.borangalleri.service;

import com.borangalleri.dto.DtoCustomer;
import com.borangalleri.dto.DtoCustomerIU;

public interface ICustomerService {
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);

}
