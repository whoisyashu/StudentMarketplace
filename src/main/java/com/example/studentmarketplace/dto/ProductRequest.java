package com.example.studentmarketplace.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 120, message = "Title must not exceed 120 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private double price;

    @NotBlank(message = "Category is required")
    private String category;
    private List<String> imageUrls;

    @NotBlank(message = "Condition is required")
    private String condition;

    @NotBlank(message = "Location is required")
    @Size(max = 120, message = "Location must not exceed 120 characters")
    private String location;
    private boolean negotiable;

    @Size(max = 300, message = "Tags must not exceed 300 characters")
    private String tags;
}
