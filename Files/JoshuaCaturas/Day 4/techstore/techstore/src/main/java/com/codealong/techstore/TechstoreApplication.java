package com.codealong.techstore;

import com.codealong.techstore.entity.Product;
import com.codealong.techstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechstoreApplication implements CommandLineRunner {

    @Autowired
    private ProductService service;

    public static void main(String[] args) {
        SpringApplication.run(TechstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- STARTING INVENTORY CHECKS ---");

        // 1. CREATE 
        Product p1 = new Product("Laptop", "Electronics", 1200.00);
        Product p2 = new Product("Mouse", "Electronics", 25.00);
        Product p3 = new Product("Desk Chair", "Furniture", 150.00);
        
        service.saveProduct(p1);
        service.saveProduct(p2);
        service.saveProduct(p3);
        
        System.out.println("--- ALL PRODUCTS AFTER SAVE ---");
        service.printAllProducts();

        // 2. READ 
        System.out.println("--- FIND BY ID (1) ---");
        Product found = service.getProductById(1L);
        System.out.println("Found: " + found);

        // 3. UPDATE 
        System.out.println("--- UPDATING PRICE OF MOUSE ---");
        service.updatePrice(2L, 19.99); // Price drop!

        // 4. DERIVED QUERIES 
        service.searchByCategory("Electronics");

        // 5. DELETE 
        System.out.println("--- DELETING ID 3 (Chair) ---");
        service.deleteProduct(3L);
        
        System.out.println("--- FINAL INVENTORY ---");
        service.printAllProducts();
    }
}
