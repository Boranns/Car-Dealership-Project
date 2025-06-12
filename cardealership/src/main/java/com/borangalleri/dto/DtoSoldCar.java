package com.borangalleri.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSoldCar extends DtoBase{
    private DtoCustomer dtoCustomer;

    private DtoGallerist dtoGallerist;

    private DtoCar dtoCar;

}
