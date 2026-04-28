package com.example.studentmarketplace.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String title;
    private String description;
    private double price;
    private String category;
    private List<String> imageUrls;
    private String condition;
    private String location;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int views;
    private int favorites;
    private boolean negotiable;
    private String tags;
    private String sellerName;
    private String sellerId;
    private double sellerRating;
}
