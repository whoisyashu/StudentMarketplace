package com.example.studentmarketplace.controller;

import com.example.studentmarketplace.domain.User;
import com.example.studentmarketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(
            @RequestBody User updatedUser,
            Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        User user = userService.updateProfile(userId, updatedUser);
        return ResponseEntity.ok(user);
    }
}
