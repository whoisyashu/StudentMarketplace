package com.example.studentmarketplace.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    private String id;
    
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String college;
    private String contactNumber;
    private String bio;
    private String profilePictureUrl;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isVerified;
    private boolean isActive;
    
    // Ratings and review info
    private double averageRating;
    private int totalReviews;
}
