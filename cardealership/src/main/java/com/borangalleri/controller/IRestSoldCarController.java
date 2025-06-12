package com.borangalleri.controller;

import com.borangalleri.dto.DtoSoldCar;
import com.borangalleri.dto.DtoSoldCarIU;

public interface IRestSoldCarController {

    public RootEntity<DtoSoldCar> buyCar(DtoSoldCarIU dtoSoldCarIU);

}
