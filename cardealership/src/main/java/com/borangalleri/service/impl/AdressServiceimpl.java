package com.borangalleri.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borangalleri.dto.DtoAdress;
import com.borangalleri.dto.DtoAdressIU;
import com.borangalleri.model.Address;
import com.borangalleri.repository.AdressRepository;
import com.borangalleri.service.IAdressService;

@Service
public class AdressServiceimpl implements IAdressService{

    @Autowired
    private AdressRepository adressRepository;

    private Address createAdress(DtoAdressIU dtoAdressIU){
        Address address = new Address();
        address.setCreateTime(LocalDateTime.now());

        BeanUtils.copyProperties(dtoAdressIU, address);

        return address;
    }
    
    
    @Override
    public DtoAdress saveAdress(DtoAdressIU dtoAdressIU) {
        DtoAdress dtoAddress =new DtoAdress();
        Address savedAddress = adressRepository.save(createAdress(dtoAdressIU));
        BeanUtils.copyProperties(savedAddress, dtoAddress);

        return dtoAddress;
    }

}
