package com.borangalleri.service;

import com.borangalleri.dto.DtoGalleristsCar;
import com.borangalleri.dto.DtoGalleristsCarIU;

public interface IGalleristsCarService {
    
    public DtoGalleristsCar saveCar(DtoGalleristsCarIU dtoGalleristsCarIU);

}
