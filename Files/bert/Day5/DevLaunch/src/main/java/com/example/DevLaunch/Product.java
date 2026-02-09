package com.example.DevLaunch;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean isOnSale) {
        this.isOnSale = isOnSale;
    }
}
