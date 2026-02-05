package com.megamart.megamart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            // Check if data exists, if not, create 100 dummy products
            if (repository.count() == 0) {
                System.out.println("🌱 Seeding Database with 100 Products...");
                
                List<Product> products = IntStream.rangeClosed(1, 100)
                    .mapToObj(i -> {
                        Product p = new Product();
                        p.setName("Product " + i);
                        p.setPrice((double) (i * 10)); // Price varies: 10, 20, 30...
                        p.setCategory(i % 2 == 0 ? "Electronics" : "Books");
                        p.setDateAdded(LocalDate.now().minusDays(i)); // Dates go backwards
                        return p;
                    })
                    .toList();
                
                repository.saveAll(products); // Batch save is more efficient
                
                System.out.println("✅ Seeding Complete.");
            }
        };
    }
}
