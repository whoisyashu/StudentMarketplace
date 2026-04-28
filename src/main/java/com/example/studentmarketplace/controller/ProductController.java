package com.example.studentmarketplace.controller;

import com.example.studentmarketplace.domain.Product;
import com.example.studentmarketplace.dto.ProductRequest;
import com.example.studentmarketplace.dto.ProductResponse;
import com.example.studentmarketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody ProductRequest request,
            Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Product product = productService.createProduct(userId, request);
        return ResponseEntity.ok(productService.convertToResponse(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(productService.convertToResponse(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable String id,
            @RequestBody ProductRequest request,
            Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Product product = productService.updateProduct(id, userId, request);
        return ResponseEntity.ok(productService.convertToResponse(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable String id,
            Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        productService.deleteProduct(id, userId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<ProductResponse>> getProductsBySeller(@PathVariable String sellerId) {
        List<Product> products = productService.getProductsBySeller(sellerId);
        return ResponseEntity.ok(products.stream()
                .map(productService::convertToResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Product> products = productService.getAllAvailableProducts(pageable);
        return ResponseEntity.ok(products.map(productService::convertToResponse));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponse>> searchProducts(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.searchByKeyword(keyword, pageable);
        return ResponseEntity.ok(products.map(productService::convertToResponse));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ProductResponse>> getProductsByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.filterByCategory(category, pageable);
        return ResponseEntity.ok(products.map(productService::convertToResponse));
    }

    @GetMapping("/price-range")
    public ResponseEntity<Page<ProductResponse>> getProductsByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.filterByPriceRange(minPrice, maxPrice, pageable);
        return ResponseEntity.ok(products.map(productService::convertToResponse));
    }

    @PutMapping("/{id}/sold")
    public ResponseEntity<String> markAsSold(
            @PathVariable String id,
            Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        productService.markAsSold(id, userId);
        return ResponseEntity.ok("Product marked as sold");
    }
}
