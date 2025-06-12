package com.borangalleri.dto;

import java.math.BigDecimal;

import com.borangalleri.enums.CurrencyType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccountIU {
    
    @NotEmpty
    private String accountNo;

    @NotEmpty
    private String iban;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private CurrencyType currencyType;

}
