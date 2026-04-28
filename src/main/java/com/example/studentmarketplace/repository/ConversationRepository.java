package com.example.studentmarketplace.repository;

import com.example.studentmarketplace.domain.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends MongoRepository<Conversation, String> {
    
    @Query("{ $or: [ { 'userId1': ?0 }, { 'userId2': ?0 } ] }")
    List<Conversation> findByUserId(String userId);
    
    @Query("{ $or: [ { $and: [ { 'userId1': ?0 }, { 'userId2': ?1 } ] }, { $and: [ { 'userId1': ?1 }, { 'userId2': ?0 } ] } ] }")
    Optional<Conversation> findConversationBetween(String userId1, String userId2);
}
