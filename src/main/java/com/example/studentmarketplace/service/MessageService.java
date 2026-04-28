package com.example.studentmarketplace.service;

import com.example.studentmarketplace.domain.Conversation;
import com.example.studentmarketplace.domain.Message;
import com.example.studentmarketplace.dto.MessageRequest;
import com.example.studentmarketplace.dto.MessageResponse;
import com.example.studentmarketplace.exception.ResourceNotFoundException;
import com.example.studentmarketplace.repository.ConversationRepository;
import com.example.studentmarketplace.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserService userService;

    public Message sendMessage(String senderId, MessageRequest request) {
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
                .content(request.getContent())
                .messageType(request.getMessageType() != null ? request.getMessageType() : "text")
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

    public List<Message> getConversationMessages(String conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }

    public List<Conversation> getUserConversations(String userId) {
        return conversationRepository.findByUserId(userId);
    }

    public void markAsRead(String messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));
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
}
