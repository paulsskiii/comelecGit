package com.codealong.techstore.service;

import com.codealong.techstore.entity.Product;
import com.codealong.techstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    // --- Create & Update ---
    public Product saveProduct(Product product) {
        // If product.id is null -> INSERT
        // If product.id exists -> UPDATE
        return repo.save(product);
    }

    // Helper to update price specifically to demonstrate Update logic
    public void updatePrice(Long id, Double newPrice) {
        // Find the product first
        Product p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot update: Product with ID " + id + " not found"));

        // Modify the object
        p.setPrice(newPrice);

        // Save it again (Update)
        repo.save(p);
    }

    // --- Reading Data with Optional ---
    public Product getProductById(Long id) {
        // We use .orElseThrow() to avoid NullPointerExceptions
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found!"));
    }

    public void printAllProducts() {
        repo.findAll().forEach(System.out::println);
    }

    // --- Derived Queries Integration ---
    public void searchByCategory(String category) {
        System.out.println("--- Finding items in: " + category + " ---");
        repo.findByCategory(category).forEach(System.out::println);
    }

    // --- Deleting Data ---
    public void deleteProduct(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            System.out.println("Product " + id + " deleted successfully.");
        } else {
            System.out.println("Cannot delete: Product " + id + " does not exist.");
        }
    }

}
