package com.example.usersystem;

import com.example.usersystem.Services.TownService;
import com.example.usersystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final UserService userService;
    private final TownService townService;

    @Autowired
    public CommandLineRunner(UserService userService, TownService townService) {
        this.userService = userService;
        this.townService = townService;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
