package com.example.studentmarketplace.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Review {

    @Id
    private String id;
    
    private String reviewerId;   // User leaving the review
    private String revieweeId;   // User being reviewed (seller)
    private String productId;    // Product being reviewed (optional)
    
    private int rating;          // 1-5
    private String comment;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
