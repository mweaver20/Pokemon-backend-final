package com.capstone.pokemonGame.services;

import com.capstone.pokemonGame.models.LoginRequest;
import com.capstone.pokemonGame.entity.User;
import com.capstone.pokemonGame.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServices {
    private final UserRepository userRepository;

    public RegistrationServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public ResponseEntity<String> registerUser(LoginRequest loginRequest) {

        // Create a new User instance and set username and password
        User newUser = new User();
        newUser.setUsername(loginRequest.getUsername());
        newUser.setPassword(loginRequest.getPassword());

        // Save the new user to the database
        userRepository.save(newUser);

        // Return a success message to the frontend
        return ResponseEntity.ok("Account successfully created");
    }

    // Check if the username already exists in the database
    public boolean usernameExists(String username) {
        if (userRepository.existsByUsername(username)) {
            return true;
        } else {
            return false;
        }
    }
}
