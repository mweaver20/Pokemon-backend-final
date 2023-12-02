package com.capstone.pokemonGame.services;

import com.capstone.pokemonGame.entity.User;
import com.capstone.pokemonGame.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LoginServices {
    private final UserRepository userRepository;

    @Autowired
    public LoginServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean verifyPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
