package com.example.springdata;

import com.example.springdata.models.Account;
import com.example.springdata.models.User;
import com.example.springdata.services.AccountService;
import com.example.springdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User("Koce", 30);

        Account account = new Account(new BigDecimal("100000"), user);

        user.setAccounts(new HashSet<>() {{
            add(account);
        }});

        this.userService.registerUser(user);
        this.accountService.registerAccount(account);

        this.accountService.withdrawMoney(new BigDecimal("100"), account.getId());
        this.accountService.depositMoney(new BigDecimal("101"), account.getId());
    }
}
