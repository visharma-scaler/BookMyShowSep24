package com.scaler.bookmyshowsep24.services;

import com.scaler.bookmyshowsep24.models.User;
import com.scaler.bookmyshowsep24.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String email, String password) {
        Optional<User> existingEmailUser = userRepository.findByEmail(email);

        if (existingEmailUser.isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        User user = new User();
        user.setEmail(email);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No user found!"));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        throw new RuntimeException("Invalid Credentials!");
    }
}

/*

        1. Check if the user is already created or not
            a. Fetch the user details by email
            b. If exists, throw error
        2. Create a new User
        3. Save the new user details
        4. Return the user

*/


// Break till 08:28 AM IST