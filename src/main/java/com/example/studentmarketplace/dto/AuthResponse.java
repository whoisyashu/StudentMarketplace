package com.example.studentmarketplace.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String message;
}
