package com.example.studentmarketplace.controller;

import com.example.studentmarketplace.domain.Conversation;
import com.example.studentmarketplace.domain.Message;
import com.example.studentmarketplace.dto.MessageRequest;
import com.example.studentmarketplace.dto.MessageResponse;
import com.example.studentmarketplace.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> sendMessage(
            @Valid @RequestBody MessageRequest request,
            Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Message message = messageService.sendMessage(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.convertToResponse(message));
    }

    @GetMapping("/conversation/{conversationId}")
    public ResponseEntity<List<MessageResponse>> getConversationMessages(
            @PathVariable String conversationId,
            Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        List<Message> messages = messageService.getConversationMessages(conversationId, userId);
        return ResponseEntity.ok(messages.stream()
                .map(messageService::convertToResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/conversations")
    public ResponseEntity<List<Conversation>> getUserConversations(
            Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        List<Conversation> conversations = messageService.getUserConversations(userId);
        return ResponseEntity.ok(conversations);
    }

    @PutMapping("/{messageId}/read")
    public ResponseEntity<String> markAsRead(@PathVariable String messageId, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        messageService.markAsRead(messageId, userId);
        return ResponseEntity.ok("Message marked as read");
    }
}
