package com.example.simpleapp.domain;

import java.math.BigDecimal;

public class FixedPricePromotion implements Promotion {
    private BigDecimal discount;

    public FixedPricePromotion(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override public BigDecimal apply(BigDecimal sellerPrice, int quantity) {
        return sellerPrice.subtract(discount.multiply(BigDecimal.valueOf(quantity)));
    }
}
