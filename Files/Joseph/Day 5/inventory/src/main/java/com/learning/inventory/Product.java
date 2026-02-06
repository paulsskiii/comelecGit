package com.learning.inventory;

public class Product {
    private String name;
    private double price;
    private int stockQuantity;
    private boolean onSale;

    public Product(String name, double price, int stockQuantity, boolean onSale) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.onSale = onSale;
    }

    // Thymeleaf uses these Getters to access data (e.g., ${product.name})
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public boolean getOnSale() { return onSale; }
}
