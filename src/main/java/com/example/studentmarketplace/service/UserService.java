package com.example.studentmarketplace.service;

import com.example.studentmarketplace.domain.User;
import com.example.studentmarketplace.dto.AuthResponse;
import com.example.studentmarketplace.dto.LoginRequest;
import com.example.studentmarketplace.dto.RegisterRequest;
import com.example.studentmarketplace.dto.UserProfileResponse;
import com.example.studentmarketplace.dto.UserProfileUpdateRequest;
import com.example.studentmarketplace.exception.ResourceNotFoundException;
import com.example.studentmarketplace.exception.UnauthorizedException;
import com.example.studentmarketplace.repository.UserRepository;
import com.example.studentmarketplace.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        // Check if email already exists
        String normalizedEmail = normalizeEmail(request.getEmail());

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Create new user
        User user = User.builder()
                .email(normalizedEmail)
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName().trim())
                .lastName(request.getLastName().trim())
                .college(request.getCollege().trim())
                .contactNumber(request.getContactNumber().trim())
                .isVerified(false)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .averageRating(0)
                .totalReviews(0)
                .build();

        User savedUser = userRepository.save(user);
        String token = jwtUtil.generateToken(savedUser.getId());

        return AuthResponse.builder()
                .token(token)
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .message("Registration successful")
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(normalizeEmail(request.getEmail()))
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getId());

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .message("Login successful")
                .build();
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserProfileResponse getUserProfileById(String id) {
        return toUserProfileResponse(getUserById(id));
    }

    public UserProfileResponse updateProfile(String userId, UserProfileUpdateRequest updatedUser) {
        User user = getUserById(userId);
        
        if (updatedUser.getFirstName() != null) user.setFirstName(updatedUser.getFirstName().trim());
        if (updatedUser.getLastName() != null) user.setLastName(updatedUser.getLastName().trim());
        if (updatedUser.getBio() != null) user.setBio(updatedUser.getBio().trim());
        if (updatedUser.getContactNumber() != null) user.setContactNumber(updatedUser.getContactNumber().trim());
        if (updatedUser.getProfilePictureUrl() != null) user.setProfilePictureUrl(updatedUser.getProfilePictureUrl().trim());
        
        user.setUpdatedAt(LocalDateTime.now());
        return toUserProfileResponse(userRepository.save(user));
    }

    private UserProfileResponse toUserProfileResponse(User user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .college(user.getCollege())
                .contactNumber(user.getContactNumber())
                .bio(user.getBio())
                .profilePictureUrl(user.getProfilePictureUrl())
                .verified(user.isVerified())
                .active(user.isActive())
                .averageRating(user.getAverageRating())
                .totalReviews(user.getTotalReviews())
                .build();
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
