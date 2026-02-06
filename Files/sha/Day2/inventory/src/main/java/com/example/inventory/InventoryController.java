package com.example.inventory;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController // Tells Spring this class handles HTTP requests
@RequestMapping("/inventory") // Base path: all URLs start with /inventory
public class InventoryController {

    // Simulating a database
    private List<Product> inventory = new ArrayList<>();

    // Let's add one item so the list isn't empty on start
    public InventoryController() {
        inventory.add(new Product(1L, "Gaming Mouse", "Electronics", 49.99));
        inventory.add(new Product(2L, "Office Chair", "Furniture", 120.00));
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product newProduct) {
        // @RequestBody takes the JSON payload and converts it into a 'newProduct' Java
        // object
        inventory.add(newProduct);
        return newProduct; // Return it back to confirm it was added
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        // @PathVariable extracts the {id} from the URL and assigns it to the Long id
        // variable
        // Logic: specific resource identification (The "Which")
        return inventory.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null); // Returns null if not found for simplicity
    }

    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam String category,
            @RequestParam(required = false, defaultValue = "false") boolean onSale) {
        return inventory.stream()
                // Added null check to prevent crashes
                .filter(p -> p.getCategory() != null && p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/update")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product updateData,
            @RequestParam(defaultValue = "false") boolean distinctLog) {
        Product existingProduct = getProductById(id);

        if (existingProduct != null) {
            existingProduct.setName(updateData.getName());
            existingProduct.setPrice(updateData.getPrice());
            // This line was missing in your Step 6 snippet:
            existingProduct.setCategory(updateData.getCategory());

            if (distinctLog) {
                System.out.println("ALERT: High priority update on item " + id);
            }
        }
        return existingProduct;
    }

}
