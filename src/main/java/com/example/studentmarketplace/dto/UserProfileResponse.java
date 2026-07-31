package com.example.studentmarketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponse {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String college;
    private String contactNumber;
    private String bio;
    private String profilePictureUrl;
    private boolean verified;
    private boolean active;
    private double averageRating;
    private int totalReviews;
}
