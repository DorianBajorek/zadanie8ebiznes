package org.example;

import org.example.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private Map<String, String> users = new HashMap<>();

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.POST})
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        if (users.containsKey(user.getUsername()) && users.get(user.getUsername()).equals(user.getPassword())) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.POST})
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (!users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user.getPassword());
            return ResponseEntity.ok("Registration successful!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
    }
}
