package com.borangalleri.controller;

import com.borangalleri.dto.DtoCustomer;
import com.borangalleri.dto.DtoCustomerIU;

public interface IRestCustomerController {

    public RootEntity<DtoCustomer> savCustomer(DtoCustomerIU dtoCustomerIU);

}
