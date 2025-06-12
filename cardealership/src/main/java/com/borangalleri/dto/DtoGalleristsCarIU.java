package com.borangalleri.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristsCarIU {
   
    @NotNull
    private Long carID;
    
    @NotNull 
    private Long galleristID;


}
