package com.example.studentmarketplace.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "products")
@CompoundIndexes({
        @CompoundIndex(name = "seller_status_idx", def = "{'sellerId': 1, 'status': 1}"),
        @CompoundIndex(name = "status_category_price_idx", def = "{'status': 1, 'category': 1, 'price': 1}")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product {

    @Id
    private String id;
    
    @Indexed
    private String sellerId;  // Reference to User.id
    private String title;
    private String description;

    @Indexed
    private double price;
    
    @Indexed
    private String category;  // books, gadgets, electronics, furniture, etc.
    private List<String> imageUrls;
    
    private String condition;  // new, like-new, used
    private String location;
    
    @Indexed
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
