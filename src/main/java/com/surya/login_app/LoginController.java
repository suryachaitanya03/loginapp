package com.surya.login_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") 
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username and password are required"));
        }

        Optional<User> user = userRepository.findByUsername(username);
        
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return ResponseEntity.ok().body(Map.of("message", "Success", "userId", user.get().getId()));
        }
        
        return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
    }
}