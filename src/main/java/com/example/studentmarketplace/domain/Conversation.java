package com.example.studentmarketplace.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "conversations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Conversation {

    @Id
    private String id;
    
    private String userId1;  // First user
    private String userId2;  // Second user
    
    private String lastMessageId;
    private LocalDateTime lastMessageAt;
    
    private String lastMessage;
    private boolean user1Unread;
    private boolean user2Unread;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
