package com.borangalleri.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.borangalleri.dto.CurrencyRatesResponse;
import com.borangalleri.dto.DtoCar;
import com.borangalleri.dto.DtoCustomer;
import com.borangalleri.dto.DtoGallerist;
import com.borangalleri.dto.DtoSoldCar;
import com.borangalleri.dto.DtoSoldCarIU;
import com.borangalleri.enums.CarStatusType;
import com.borangalleri.exception.BaseException;
import com.borangalleri.exception.ErrorMessage;
import com.borangalleri.exception.MessageType;
import com.borangalleri.model.Car;
import com.borangalleri.model.Customer;
import com.borangalleri.model.SoldCar;
import com.borangalleri.repository.CarRepository;
import com.borangalleri.repository.CustomerRepository;
import com.borangalleri.repository.GalleristRepository;
import com.borangalleri.repository.SoldCarRepository;
import com.borangalleri.service.ICurrencyRatesService;
import com.borangalleri.service.ISoldCarService;
import com.borangalleri.utils.DateUtils;

@Service
public class SoldCarServiceImpl implements ISoldCarService{

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private SoldCarRepository soldCarRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    public BigDecimal convertCustomerAmountToUSD(Customer customer){
        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(
            DateUtils.getCurrentDate(LocalDateTime.now()), 
            DateUtils.getCurrentDate(LocalDateTime.now()));

        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        BigDecimal customerUSDamount = customer.getAccount()
        .getAmount()
        .divide(usd,2,RoundingMode.HALF_UP);

        return customerUSDamount;
    }

    public boolean checkCarStatus(Long carID){
        Optional<Car> optCar = carRepository.findById(carID);
        if (optCar.isPresent() && optCar.get()
        .getCarStatusType()
        .name()
        .equals(CarStatusType.SOLD
        .name())) {
            return false;
        }
        return true;
        }

    public BigDecimal remainingCustomerAmount(Customer customer, Car car){
        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
        BigDecimal remainingCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());


        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates
        (DateUtils.getCurrentDate(LocalDateTime.now()), 
         DateUtils.getCurrentDate(LocalDateTime.now()));

        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        return remainingCustomerUSDAmount.multiply(usd);
    }

    public boolean checkAmount(DtoSoldCarIU dtoSoldCarIU){
        Optional<Customer> optCustomer = customerRepository.findById(dtoSoldCarIU.getCustomerID());
        if (optCustomer.isEmpty()) {
            throw new BaseException
            (new ErrorMessage
            (MessageType.NO_RECORD_EXIST,
            dtoSoldCarIU.getCustomerID()
            .toString()));
        }
        Optional<Car> optCar = carRepository.findById(dtoSoldCarIU.getCarID());

        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage
            (MessageType.NO_RECORD_EXIST, 
            dtoSoldCarIU.getCarID()
            .toString()));
        }

        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());

        if (customerUSDAmount.compareTo(optCar.get().getPrice())== 0 || 
            customerUSDAmount.compareTo(optCar.get().getPrice())> 0) {
            return true;
        }
        return false;
    }

    private SoldCar createSoldCar(DtoSoldCarIU dtoSoldCarIU){
        SoldCar soldCar = new SoldCar();
        soldCar.setCreateTime(LocalDateTime.now());
        soldCar.setCustomer(customerRepository.findById(dtoSoldCarIU.getCustomerID()).orElse(null));
        soldCar.setGallerist(galleristRepository.findById(dtoSoldCarIU.getGalleristID()).orElse(null));
        soldCar.setCar(carRepository.findById(dtoSoldCarIU.getCarID()).orElse(null));

        return soldCar;
    }

    @Override
    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU) {
        if (!checkAmount(dtoSoldCarIU)) {
            throw new BaseException(new ErrorMessage
            (MessageType.CUSTOMER_AMOUNT_NOT_ENOUGH, 
            ""));
            
        }
        
        if (!checkCarStatus(dtoSoldCarIU.getCarID())) {
            throw new BaseException
            (new ErrorMessage
            (MessageType.CAR_ALREADY_SOLD, 
            dtoSoldCarIU
            .getCarID()
            .toString()));
        }


        SoldCar savedSoldCar = soldCarRepository.save(createSoldCar(dtoSoldCarIU));

        Car car = savedSoldCar.getCar();
        car.setCarStatusType(CarStatusType.SOLD);

        carRepository.save(car);

        Customer customer = savedSoldCar.getCustomer();
        customer.getAccount().setAmount(remainingCustomerAmount(customer, car));
        customerRepository.save(customer);
        return toDto(savedSoldCar);
    }

    public DtoSoldCar toDto(SoldCar soldcar){
        DtoSoldCar dtoSoldCar = new DtoSoldCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        BeanUtils.copyProperties(soldcar, dtoSoldCar);
        BeanUtils.copyProperties(soldcar.getCustomer(), dtoCustomer);
        BeanUtils.copyProperties(soldcar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(soldcar.getCar(), dtoCar);

        dtoSoldCar.setDtoCustomer(dtoCustomer);
        dtoSoldCar.setDtoGallerist(dtoGallerist);
        dtoSoldCar.setDtoCar(dtoCar);

        return dtoSoldCar;
    }

}
