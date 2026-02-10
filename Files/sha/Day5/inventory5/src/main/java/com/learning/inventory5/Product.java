package com.learning.inventory5;

public class Product {
    private String name;
    private double price;
    private int stockQuantity;
    private boolean isOnSale;

    public Product(String name, double price, int stockQuantity, boolean isOnSale) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.isOnSale = isOnSale;
    }

    // Thymeleaf uses these Getters to access data (e.g., ${product.name})
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public boolean getIsOnSale() {
        return isOnSale;
    }

}
