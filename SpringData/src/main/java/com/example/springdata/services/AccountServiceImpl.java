package com.example.springdata.services;

import com.example.springdata.models.Account;
import com.example.springdata.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerAccount(Account account) {
        Account accountById = this.accountRepository.findAccountById(account.getId());

        if (accountById == null) {
            this.accountRepository.save(account);
        }
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Account accountById = this.accountRepository.findAccountById(id);

        if (accountById != null) {
            accountById.setBalance(accountById.getBalance().subtract(money));
            this.accountRepository.saveAndFlush(accountById);
        }
    }

    @Override
    public void depositMoney(BigDecimal money, Long id) {
        Account accountById = this.accountRepository.findAccountById(id);

        if (accountById != null) {
            accountById.setBalance(accountById.getBalance().add(money));
            this.accountRepository.saveAndFlush(accountById);
        }


    }
}
