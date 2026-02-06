package com.learning.inventory;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InventoryController {

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        // 1. Simulate fetching data from a database
        List<Product> myProducts = Arrays.asList(
            new Product("Gaming Laptop", 1200.00, 5),   // In stock
            new Product("Mechanical Keyboard", 150.00, 0), // Out of stock
            new Product("Wireless Mouse", 45.50, 50),     // Plenty of stock
            new Product("HD Monitor", 300.00, 1)          // Low stock
        );

        // 2. Pass the list to the HTML view
        // "products" is the key we will use in the th:each loop
        model.addAttribute("products", myProducts);

        return "inventory"; // Looks for inventory.html
    }
}
