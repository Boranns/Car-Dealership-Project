package com.borangalleri.controller;

import com.borangalleri.dto.DtoCar;
import com.borangalleri.dto.DtoCarIU;

public interface IRestCarController {
    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);

}
