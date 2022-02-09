package com.example.simpleapp.domain;

import java.util.ArrayList;
import java.util.List;

public class BasketItem {
    private Product product;
    private int quantity;
    private List<Promotion> associatedPromotions = new ArrayList<>();

    public BasketItem() {
    }

    public BasketItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<Promotion> getAssociatedPromotions() {
        return associatedPromotions;
    }

    public void setAssociatedPromotions(List<Promotion> associatedPromotions) {
        this.associatedPromotions = associatedPromotions;
    }

    public void incQuantity(int count) {
        this.quantity += count;
    }

    @Override public String toString() {
        return "BasketItem{" +
               "product=" + product + "\n" +
               ", quantity=" + quantity + "\n" +
               ", associatedPromotions=" + associatedPromotions + "\n" +
               '}';
    }
}

