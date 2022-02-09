package com.example.simpleapp.domain;

import java.math.BigDecimal;

public interface Promotion {
    public BigDecimal apply(BigDecimal sellerPrice, int quantity);
}
