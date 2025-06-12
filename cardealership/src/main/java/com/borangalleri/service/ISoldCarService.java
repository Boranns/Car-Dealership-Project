package com.borangalleri.service;

import com.borangalleri.dto.DtoSoldCar;
import com.borangalleri.dto.DtoSoldCarIU;

public interface ISoldCarService {

    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU);
}
