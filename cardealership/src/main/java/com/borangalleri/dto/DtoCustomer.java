package com.borangalleri.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCustomer extends DtoBase{

    private String firstName;

    private String lastName;

    private String tckn;

    private Date birthOfDate;

    private DtoAdress adress;

    private DtoAccount account;

    

}
