package com.example.studentmarketplace.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String title;
    private String description;
    private double price;
    private String category;
    private List<String> imageUrls;
    private String condition;
    private String location;
    private boolean negotiable;
    private String tags;
}
