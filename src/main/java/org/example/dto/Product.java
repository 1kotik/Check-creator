package org.example.dto;

public class Product {
    int id;
    int quantity;
    double price;
    String name;
    boolean wholesale;

    public Product(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setWholesale(boolean wholesale) {
        this.wholesale = wholesale;
    }

    public boolean getWholesale() {
        return wholesale;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
}
