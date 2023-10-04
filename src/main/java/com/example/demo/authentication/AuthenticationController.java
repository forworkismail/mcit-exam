package com.example.demo.authentication;

import com.example.demo.authentication.requests.LoginRequest;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthenticationController {

    private final UserRepository userRepository;

    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // for login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Find the user by the provided username
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.username());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Check if the provided password matches the user's password
            if (loginRequest.password().equals(user.getPassword())) {
                return ResponseEntity.ok(user.getRole()); // Successful login
            }
        }

        return ResponseEntity.ok(false); // Failed login
    }

}