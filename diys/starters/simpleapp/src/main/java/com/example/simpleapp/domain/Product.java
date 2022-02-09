package com.example.simpleapp.domain;

import java.math.BigDecimal;

public class Product {
    private String id;
    private String name;
    private BigDecimal sellerPrice;

    public Product(String id, String name, BigDecimal sellerPrice) {
        this.id = id;
        this.name = name;
        this.sellerPrice = sellerPrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSellerPrice() {
        return sellerPrice;
    }

    @Override public String toString() {
        return "Product{" +
               "id='" + id + '\'' + "\n" +
               ", name='" + name + '\'' + "\n" +
               ", sellerPrice=" + sellerPrice + "\n" +
               '}';
    }
}
