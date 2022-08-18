package com.example.springdata.services;

import com.example.springdata.models.User;
import com.example.springdata.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        User userByUsername = this.userRepository.getUserByUsername(user.getUsername());

        if (userByUsername == null) {
            this.userRepository.saveAndFlush(user);
        }
    }
}
