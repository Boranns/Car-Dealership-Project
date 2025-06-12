package com.borangalleri.controller;

import com.borangalleri.dto.DtoGallerist;
import com.borangalleri.dto.DtoGalleristIU;

public interface IRestGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

}
