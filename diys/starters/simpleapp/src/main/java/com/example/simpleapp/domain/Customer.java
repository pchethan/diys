package com.example.simpleapp.domain;

import java.util.UUID;

public class Customer {
    private UUID customerId;
    private String type;

    public Customer(UUID customerId, String type) {
        this.customerId = customerId;
        this.type = type;
    }
}
