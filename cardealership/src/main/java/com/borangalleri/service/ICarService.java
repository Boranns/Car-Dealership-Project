package com.borangalleri.service;

import com.borangalleri.dto.DtoCar;
import com.borangalleri.dto.DtoCarIU;

public interface ICarService {
   public DtoCar saveCar(DtoCarIU dtoCarIU);

}
