package com.company.MMK.service;

import com.company.MMK.repository.AccountRepository;
import com.company.MMK.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Autowired
    PhoneNumberRepository phoneNumberRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean contains(String number, Long accountId) {
        return phoneNumberRepository.findByNumberAndAccount_Id(number, accountId) != null;
    }
}
