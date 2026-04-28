package com.example.studentmarketplace.repository;

import com.example.studentmarketplace.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    
    List<Product> findBySellerId(String sellerId);
    
    @Query("{ 'status': 'available' }")
    Page<Product> findAllAvailable(Pageable pageable);
    
    @Query("{ 'category': ?0, 'status': 'available' }")
    Page<Product> findByCategory(String category, Pageable pageable);
    
    @Query("{ 'price': { $gte: ?0, $lte: ?1 }, 'status': 'available' }")
    Page<Product> findByPriceRange(double minPrice, double maxPrice, Pageable pageable);
    
    @Query("{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'description': { $regex: ?0, $options: 'i' } }, { 'tags': { $regex: ?0, $options: 'i' } } ], 'status': 'available' }")
    Page<Product> searchByKeyword(String keyword, Pageable pageable);
    
    List<Product> findByStatus(String status);
}
