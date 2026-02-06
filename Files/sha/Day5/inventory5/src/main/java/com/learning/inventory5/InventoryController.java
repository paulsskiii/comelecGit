package com.learning.inventory5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class InventoryController {

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        // 1. Simulate fetching data from a database
        List<Product> myProducts = Arrays.asList(
                new Product("Gaming Laptop", 1200.00, 5, true), // In stock
                new Product("Mechanical Keyboard", 150.00, 0, false), // Out of stock
                new Product("Wireless Mouse", 45.50, 50, true), // Plenty of stock
                new Product("HD Monitor", 300.00, 1, false) // Low stock
        );

        // 2. Pass the list to the HTML view
        // "products" is the key we will use in the th:each loop
        model.addAttribute("products", myProducts);

        return "inventory"; // Looks for inventory.html
    }
}
