package com.example.studentmarketplace.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product {

    @Id
    private String id;
    
    private String sellerId;  // Reference to User.id
    private String title;
    private String description;
    private double price;
    
    private String category;  // books, gadgets, electronics, furniture, etc.
    private List<String> imageUrls;
    
    private String condition;  // new, like-new, used
    private String location;
    
    private String status;  // available, sold, pending
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime soldAt;
    
    private int views;
    private int favorites;
    
    // Additional fields for future features
    private boolean negotiable;
    private String tags;
}
