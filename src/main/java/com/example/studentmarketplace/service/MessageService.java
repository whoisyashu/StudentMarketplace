package com.example.studentmarketplace.service;

import com.example.studentmarketplace.domain.Conversation;
import com.example.studentmarketplace.domain.Message;
import com.example.studentmarketplace.dto.MessageRequest;
import com.example.studentmarketplace.dto.MessageResponse;
import com.example.studentmarketplace.exception.ResourceNotFoundException;
import com.example.studentmarketplace.exception.UnauthorizedException;
import com.example.studentmarketplace.repository.ConversationRepository;
import com.example.studentmarketplace.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserService userService;

    public MessageService(MessageRepository messageRepository, ConversationRepository conversationRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userService = userService;
    }

    public Message sendMessage(String senderId, MessageRequest request) {
        if (senderId.equals(request.getReceiverId())) {
            throw new IllegalArgumentException("You cannot send a message to yourself");
        }

        userService.getUserById(request.getReceiverId());

        // Create or get conversation
        Optional<Conversation> existingConv = conversationRepository
                .findConversationBetween(senderId, request.getReceiverId());

        Conversation conversation;
        if (existingConv.isPresent()) {
            conversation = existingConv.get();
        } else {
            conversation = Conversation.builder()
                    .userId1(senderId)
                    .userId2(request.getReceiverId())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            conversation = conversationRepository.save(conversation);
        }

        // Create message
        Message message = Message.builder()
                .senderId(senderId)
                .receiverId(request.getReceiverId())
                .conversationId(conversation.getId())
                .content(request.getContent().trim())
                .messageType(request.getMessageType() != null && !request.getMessageType().isBlank() ? request.getMessageType().trim() : "text")
                .productId(request.getProductId())
                .sentAt(LocalDateTime.now())
                .read(false)
                .build();

        Message savedMessage = messageRepository.save(message);

        // Update conversation
        conversation.setLastMessageId(savedMessage.getId());
        conversation.setLastMessage(savedMessage.getContent());
        conversation.setLastMessageAt(LocalDateTime.now());
        conversation.setUpdatedAt(LocalDateTime.now());
        conversationRepository.save(conversation);

        return savedMessage;
    }

    public List<Message> getConversationMessages(String conversationId, String userId) {
        Conversation conversation = getConversation(conversationId);
        validateConversationParticipant(conversation, userId);
        return messageRepository.findByConversationIdOrderBySentAtAsc(conversationId);
    }

    public List<Conversation> getUserConversations(String userId) {
        return conversationRepository.findByUserIdOrderByUpdatedAtDesc(userId);
    }

    public void markAsRead(String messageId, String userId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        if (!userId.equals(message.getReceiverId())) {
            throw new UnauthorizedException("You can only mark your own received messages as read");
        }
        message.setRead(true);
        message.setReadAt(LocalDateTime.now());
        messageRepository.save(message);
    }

    public MessageResponse convertToResponse(Message message) {
        var sender = userService.getUserById(message.getSenderId());
        return MessageResponse.builder()
                .id(message.getId())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .content(message.getContent())
                .sentAt(message.getSentAt())
                .read(message.isRead())
                .messageType(message.getMessageType())
                .productId(message.getProductId())
                .senderName(sender.getFirstName() + " " + sender.getLastName())
                .build();
    }

    private Conversation getConversation(String conversationId) {
        return conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation not found"));
    }

    private void validateConversationParticipant(Conversation conversation, String userId) {
        if (!userId.equals(conversation.getUserId1()) && !userId.equals(conversation.getUserId2())) {
            throw new UnauthorizedException("You are not authorized to access this conversation");
        }
    }
}
