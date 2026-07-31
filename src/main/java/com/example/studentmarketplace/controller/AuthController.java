package com.example.studentmarketplace.controller;

import com.example.studentmarketplace.dto.AuthResponse;
import com.example.studentmarketplace.dto.LoginRequest;
import com.example.studentmarketplace.dto.RegisterRequest;
import com.example.studentmarketplace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = userService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify")
    public ResponseEntity<AuthResponse> verifyToken() {
        return ResponseEntity.ok(AuthResponse.builder()
                .message("Token is valid")
                .build());
    }
}
