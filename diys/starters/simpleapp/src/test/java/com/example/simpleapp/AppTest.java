package com.example.simpleapp;

import com.example.simpleapp.domain.Basket;
import com.example.simpleapp.domain.Customer;
import com.example.simpleapp.domain.Product;
import com.example.simpleapp.exceptions.CarLotFullException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Basket basket;
    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer(UUID.randomUUID(), "Regular");
        basket = new Basket(customer);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void verifyBasketPriceComputation() {
        List<Product> inventory = new ArrayList<>();
        Product waterBottle = new Product("1", "Water", BigDecimal.valueOf(1.0));
        inventory.add(waterBottle);

        basket.addItem(waterBottle);
        basket.addItem(waterBottle);

        assertEquals("1.6",String.valueOf(basket.computeBasketCost()));
    }


    private Exception doTest(MyConsumer consumer) {
        try {
            consumer.accept();
        } catch (Exception e) {
            return e;
        }

        return null;
    }

}
