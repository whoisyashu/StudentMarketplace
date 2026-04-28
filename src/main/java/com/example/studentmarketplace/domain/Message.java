package com.example.studentmarketplace.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Message {

    @Id
    private String id;
    
    private String senderId;      // Reference to User.id
    private String receiverId;    // Reference to User.id
    private String conversationId; // To group messages between two users
    
    private String content;
    private LocalDateTime sentAt;
    private boolean read;
    private LocalDateTime readAt;
    
    // Optional: for attaching product info
    private String productId;
    
    // Message type: text, image, file
    private String messageType;
}
