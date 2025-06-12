package com.borangalleri.service;

import com.borangalleri.dto.DtoGallerist;
import com.borangalleri.dto.DtoGalleristIU;

public interface IGalleristService {

    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);

}
