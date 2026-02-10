package com.mega_mart.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // 1. Standard Pagination (Admin View)
    // Example: /api/products/admin?page=0&size=10&sort=price
    @GetMapping("/admin")
    public ResponseEntity<Page<Product>> getAdminView(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, // Default 10 items
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        // SECURITY CHECK: Prevent massive data requests
        if (size > 50) {
            size = 50; 
        }
        
        return ResponseEntity.ok(service.getProductsForAdmin(page, size, sortBy, sortDir));
    }

    // 2. Slice Pagination (Customer View - Infinite Scroll)
    // Example: /api/products/shop?page=0
    @GetMapping("/shop")
    public ResponseEntity<Slice<Product>> getCustomerView(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(service.getProductsForCustomer(page, size));
    }
}
