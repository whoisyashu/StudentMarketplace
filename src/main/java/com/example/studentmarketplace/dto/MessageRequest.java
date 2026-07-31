package com.example.studentmarketplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageRequest {
    @NotBlank(message = "Receiver ID is required")
    private String receiverId;

    @NotBlank(message = "Message content is required")
    @Size(max = 2000, message = "Message content must not exceed 2000 characters")
    private String content;

    @Size(max = 20, message = "Message type must not exceed 20 characters")
    private String messageType;
    private String productId;
}
