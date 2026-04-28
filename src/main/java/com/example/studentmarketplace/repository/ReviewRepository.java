package com.example.studentmarketplace.repository;

import com.example.studentmarketplace.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    
    @Query("{ 'revieweeId': ?0 }")
    List<Review> findByRevieweeId(String revieweeId);
    
    @Query("{ 'reviewerId': ?0 }")
    List<Review> findByReviewerId(String reviewerId);
    
    @Query("{ 'productId': ?0 }")
    List<Review> findByProductId(String productId);
}
