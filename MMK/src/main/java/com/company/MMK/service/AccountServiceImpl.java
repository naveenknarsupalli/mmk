package com.company.MMK.service;

import com.company.MMK.entity.Account;
import com.company.MMK.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Long getAccountIdByUserName(String username) {
        Account account = accountRepository.findAccountByUsername(username);
        return account.getId();
    }
}
