package com.mega_mart.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // SCENARIO 1: Admin Dashboard (Needs total pages count)
    // Returns Page<T> (Slower, 2 Queries: Select + Count)
    public Page<Product> getProductsForAdmin(int page, int size, String sortBy, String dir) {
        
        // Handling Sort Direction
        Sort sort = dir.equalsIgnoreCase("desc") ? 
                    Sort.by(sortBy).descending() : 
                    Sort.by(sortBy).ascending();

        // Zero-based indexing
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable);
    }

    // SCENARIO 2: Customer App (Infinite Scroll)
    // Returns Slice<T> (Faster, 1 Query: Limit + 1)
    public Slice<Product> getProductsForCustomer(int page, int size) {
        
        // Hardcoded sort for customers: Newest first
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateAdded").descending());

        // Use our custom method to avoid the count query!
        return repository.findBy(pageable);
    }
}
