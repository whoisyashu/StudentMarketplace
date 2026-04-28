package com.example.studentmarketplace.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private LocalDateTime sentAt;
    private boolean read;
    private String messageType;
    private String productId;
    private String senderName;
}
