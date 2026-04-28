package com.example.studentmarketplace.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageRequest {
    private String receiverId;
    private String content;
    private String messageType;
    private String productId;
}
