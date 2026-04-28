package com.example.studentmarketplace.service;

import com.example.studentmarketplace.domain.Product;
import com.example.studentmarketplace.dto.ProductRequest;
import com.example.studentmarketplace.dto.ProductResponse;
import com.example.studentmarketplace.exception.ResourceNotFoundException;
import com.example.studentmarketplace.exception.UnauthorizedException;
import com.example.studentmarketplace.repository.ProductRepository;
import com.example.studentmarketplace.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    public Product createProduct(String sellerId, ProductRequest request) {
        Product product = Product.builder()
                .sellerId(sellerId)
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .imageUrls(request.getImageUrls())
                .condition(request.getCondition())
                .location(request.getLocation())
                .status("available")
                .negotiable(request.isNegotiable())
                .tags(request.getTags())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .views(0)
                .favorites(0)
                .build();

        return productRepository.save(product);
    }

    public Product getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        // Increment views
        product.setViews(product.getViews() + 1);
        productRepository.save(product);
        return product;
    }

    public Product updateProduct(String productId, String userId, ProductRequest request) {
        Product product = getProductById(productId);
        
        if (!product.getSellerId().equals(userId)) {
            throw new UnauthorizedException("You can only edit your own products");
        }

        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setImageUrls(request.getImageUrls());
        product.setCondition(request.getCondition());
        product.setLocation(request.getLocation());
        product.setNegotiable(request.isNegotiable());
        product.setTags(request.getTags());
        product.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    public void deleteProduct(String productId, String userId) {
        Product product = getProductById(productId);
        
        if (!product.getSellerId().equals(userId)) {
            throw new UnauthorizedException("You can only delete your own products");
        }

        productRepository.delete(product);
    }

    public List<Product> getProductsBySeller(String sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    public Page<Product> getAllAvailableProducts(Pageable pageable) {
        return productRepository.findAllAvailable(pageable);
    }

    public Page<Product> searchByKeyword(String keyword, Pageable pageable) {
        return productRepository.searchByKeyword(keyword, pageable);
    }

    public Page<Product> filterByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable);
    }

    public Page<Product> filterByPriceRange(double minPrice, double maxPrice, Pageable pageable) {
        return productRepository.findByPriceRange(minPrice, maxPrice, pageable);
    }

    public void markAsSold(String productId, String userId) {
        Product product = getProductById(productId);
        
        if (!product.getSellerId().equals(userId)) {
            throw new UnauthorizedException("You can only mark your own products as sold");
        }

        product.setStatus("sold");
        product.setSoldAt(LocalDateTime.now());
        productRepository.save(product);
    }

    public ProductResponse convertToResponse(Product product) {
        var seller = userService.getUserById(product.getSellerId());
        var reviews = reviewRepository.findByRevieweeId(product.getSellerId());
        double avgRating = reviews.isEmpty() ? 0 : reviews.stream().mapToDouble(r -> r.getRating()).average().orElse(0);

        return ProductResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .imageUrls(product.getImageUrls())
                .condition(product.getCondition())
                .location(product.getLocation())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .views(product.getViews())
                .favorites(product.getFavorites())
                .negotiable(product.isNegotiable())
                .tags(product.getTags())
                .sellerName(seller.getFirstName() + " " + seller.getLastName())
                .sellerId(product.getSellerId())
                .sellerRating(avgRating)
                .build();
    }
}
