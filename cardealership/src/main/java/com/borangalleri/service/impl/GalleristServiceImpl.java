package com.borangalleri.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borangalleri.exception.BaseException;
import com.borangalleri.dto.DtoAdress;
import com.borangalleri.dto.DtoGallerist;
import com.borangalleri.dto.DtoGalleristIU;
import com.borangalleri.exception.ErrorMessage;
import com.borangalleri.exception.MessageType;
import com.borangalleri.model.Address;
import com.borangalleri.model.Gallerist;
import com.borangalleri.repository.AdressRepository;
import com.borangalleri.repository.GalleristRepository;
import com.borangalleri.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AdressRepository addressRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
        Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressID());
        if (optAddress.isEmpty()) {
            throw new BaseException(
                new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressID().toString()));
        }

        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(LocalDateTime.now());

        BeanUtils.copyProperties(dtoGalleristIU, gallerist);
        gallerist.setAddress(optAddress.get());

        return gallerist;
    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAdress dtoAddress = new DtoAdress();

        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));

        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);

        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;
    }

}
