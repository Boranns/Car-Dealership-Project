package com.borangalleri.controller;

import com.borangalleri.dto.DtoGalleristsCar;
import com.borangalleri.dto.DtoGalleristsCarIU;

public interface IRestGalleristsController {

    public RootEntity<DtoGalleristsCar> saveGalleristsCar(DtoGalleristsCarIU galleristCarIU);

}
