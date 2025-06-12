package com.borangalleri.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borangalleri.dto.DtoAccount;
import com.borangalleri.dto.DtoAdress;
import com.borangalleri.dto.DtoCustomer;
import com.borangalleri.dto.DtoCustomerIU;
import com.borangalleri.exception.BaseException;
import com.borangalleri.exception.ErrorMessage;
import com.borangalleri.exception.MessageType;
import com.borangalleri.model.Account;
import com.borangalleri.model.Address;
import com.borangalleri.model.Customer;
import com.borangalleri.repository.AccountRepository;
import com.borangalleri.repository.AdressRepository;
import com.borangalleri.repository.CustomerRepository;
import com.borangalleri.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
        Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAdressID());
        if (optAddress.isEmpty()) {
            throw new BaseException(
                new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAdressID().toString()));
        }

        Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountID());
        if (optAccount.isEmpty()) {
            throw new BaseException(
                new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountID().toString()));
        }

        Customer customer = new Customer();
        customer.setCreateTime(LocalDateTime.now());
        customer.setAddress(optAddress.get());
        customer.setAccount(optAccount.get());

        BeanUtils.copyProperties(dtoCustomerIU, customer);

        return customer;
    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAdress dtoAddress = new DtoAdress();
        DtoAccount dtoAccount = new DtoAccount();

        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

        BeanUtils.copyProperties(savedCustomer, dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);

        dtoCustomer.setAdress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);

        return dtoCustomer;
    }
}
