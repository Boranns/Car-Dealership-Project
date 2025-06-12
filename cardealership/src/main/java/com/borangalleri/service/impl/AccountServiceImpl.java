package com.borangalleri.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borangalleri.dto.DtoAccount;
import com.borangalleri.dto.DtoAccountIU;
import com.borangalleri.model.Account;
import com.borangalleri.repository.AccountRepository;
import com.borangalleri.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{

    @Autowired
    private AccountRepository accountRepository;

    private Account createAccount(DtoAccountIU dtoAccountIU){
        Account account = new Account();
        account.setCreateTime(LocalDateTime.now());
        BeanUtils.copyProperties(dtoAccountIU, account);

        return account;
    }

    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
        DtoAccount dtoAccount = new DtoAccount();
        
        Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));
       
        BeanUtils.copyProperties(savedAccount, dtoAccount);

        return dtoAccount;
    }

}
