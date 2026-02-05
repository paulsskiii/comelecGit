package com.mega_mart.inventory;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Standard findAll returns Page<T> automatically.
    
    // Custom method returning Slice<T>. 
    // Because we return Slice, Spring Data will strictly SKIP the "Count(*)" query.
    // "findBy" with no arguments implies "Find All".
    Slice<Product> findBy(Pageable pageable);
}
