package com.borangalleri.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSoldCarIU {
    
    @NotNull
    private Long customerID;

    @NotNull
    private Long galleristID;

    @NotNull
    private Long carID;

}
