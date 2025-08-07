package com.example.CRM.controller;

import com.example.CRM.entity.User;
import com.example.CRM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    // SIGNUP
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already exists";
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return "Password cannot be empty";
        }

        if (user.getPlan() == null || user.getPlan().isEmpty()) {
            return "Plan is required";
        }

        // Set default role if not provided
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER"); // Important for Spring Security
        }

        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "User registered successfully";
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty()) {
            Map<String, Object> response = Map.of("message", "Invalid email");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        User user = userOptional.get();

        // Use PasswordEncoder to check raw password against hashed password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            Map<String, Object> response = Map.of("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // Successful login
        Map<String, Object> response = Map.of(
                "message", "Login successful",
                "role", user.getRole(),
                "id", user.getId()
        );
        return ResponseEntity.ok(response);
    }
}