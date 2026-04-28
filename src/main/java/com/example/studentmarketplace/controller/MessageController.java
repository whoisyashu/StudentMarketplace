package com.example.studentmarketplace.controller;

import com.example.studentmarketplace.domain.Conversation;
import com.example.studentmarketplace.domain.Message;
import com.example.studentmarketplace.dto.MessageRequest;
import com.example.studentmarketplace.dto.MessageResponse;
import com.example.studentmarketplace.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> sendMessage(
            @RequestBody MessageRequest request,
            Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Message message = messageService.sendMessage(userId, request);
        return ResponseEntity.ok(messageService.convertToResponse(message));
    }

    @GetMapping("/conversation/{conversationId}")
    public ResponseEntity<List<MessageResponse>> getConversationMessages(
            @PathVariable String conversationId) {
        List<Message> messages = messageService.getConversationMessages(conversationId);
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
    public ResponseEntity<String> markAsRead(@PathVariable String messageId) {
        messageService.markAsRead(messageId);
        return ResponseEntity.ok("Message marked as read");
    }
}
