package com.example.studentmarketplace.controller;

import com.example.studentmarketplace.dto.UserProfileResponse;
import com.example.studentmarketplace.dto.UserProfileUpdateRequest;
import com.example.studentmarketplace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        UserProfileResponse user = userService.getUserProfileById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> getUserById(@PathVariable String userId) {
        UserProfileResponse user = userService.getUserProfileById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileResponse> updateProfile(
            @Valid @RequestBody UserProfileUpdateRequest updatedUser,
            Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        UserProfileResponse user = userService.updateProfile(userId, updatedUser);
        return ResponseEntity.ok(user);
    }
}
