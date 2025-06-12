package com.borangalleri.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristsCar extends DtoBase{

    private DtoGallerist gallerist;

    private DtoCar car;

}
