package com.borangalleri.controller;

import com.borangalleri.dto.DtoAdress;
import com.borangalleri.dto.DtoAdressIU;

public interface IRestAddressController {

    public RootEntity<DtoAdress> saveAddress(DtoAdressIU dtoAddressIU);
}
