package com.example.studentmarketplace.repository;

import com.example.studentmarketplace.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    
    @Query(value = "{ 'conversationId': ?0 }", sort = "{ 'sentAt': 1 }")
    List<Message> findByConversationIdOrderBySentAtAsc(String conversationId);
}
