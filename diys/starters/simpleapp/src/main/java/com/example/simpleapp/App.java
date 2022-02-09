package com.example.simpleapp;

import com.example.simpleapp.domain.Basket;
import com.example.simpleapp.domain.Customer;
import com.example.simpleapp.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        List<Product> inventory = new ArrayList<>();
        Product waterBottle = new Product("1", "Water", BigDecimal.valueOf(1.0));
        inventory.add(waterBottle);

        Customer customer = new Customer(UUID.randomUUID(), "Regular");
        Basket basket = new Basket(customer);

        basket.addItem(waterBottle);
        basket.addItem(waterBottle);

        System.out.println("Basket Price:");
        System.out.println(basket.computeBasketCost());

        System.out.println("Basket contents:");
        System.out.println(basket);
    }
}
