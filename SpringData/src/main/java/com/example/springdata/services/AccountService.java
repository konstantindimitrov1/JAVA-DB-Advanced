package com.example.springdata.services;

import com.example.springdata.models.Account;

import java.math.BigDecimal;

public interface AccountService {
    void registerAccount(Account account);

    void withdrawMoney(BigDecimal money, Long id);

    void depositMoney(BigDecimal money, Long id);
}
