package org.example.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerInfo {
    List<Product> products;
    DiscountCard discountCard;
    double balanceDebitCard;

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public void setBalanceDebitCard(double balanceDebitCard) {
        this.balanceDebitCard = balanceDebitCard;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getBalanceDebitCard() {
        return balanceDebitCard;
    }

    public List<Product> getProducts() {
        return products;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }
}
