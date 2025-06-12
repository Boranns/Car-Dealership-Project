package com.borangalleri.service;

import com.borangalleri.dto.DtoAdress;
import com.borangalleri.dto.DtoAdressIU;


public interface IAdressService {

    public DtoAdress saveAdress(DtoAdressIU dtoAdressIU);

}
