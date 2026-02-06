package com.codealong.techstore.repository;

import com.codealong.techstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
// We extend JpaRepository<Entity, PrimaryKeyType>
public interface ProductRepository extends JpaRepository<Product, Long> {

    // --- Derived Query Methods ---
    // Spring generates the SQL: SELECT * FROM product WHERE category = ?
    List<Product> findByCategory(String category);

    // SQL: SELECT * FROM product WHERE price < ?
    List<Product> findByPriceLessThan(Double maxPrice);
}
