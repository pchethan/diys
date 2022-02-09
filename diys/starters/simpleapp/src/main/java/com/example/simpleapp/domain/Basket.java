package com.example.simpleapp.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Basket {
    private ConcurrentHashMap<String, BasketItem> productList = new ConcurrentHashMap<String, BasketItem>();
    private Customer basketOwner;

    public Basket(Customer basketOwner) {
        this.basketOwner = basketOwner;
    }

    public Customer getBasketOwner() {
        return basketOwner;
    }

    public void setBasketOwner(Customer basketOwner) {
        this.basketOwner = basketOwner;
    }

    public void addItem(Product product) {
        BasketItem basketItem = productList.get(product.getId());
        if(basketItem != null) {
            basketItem.incQuantity(1);
        } else {
            basketItem = new BasketItem(product);
            productList.put(product.getId(), basketItem);
        }

        if(basketItem.getAssociatedPromotions().isEmpty()) {
//            basketItem.setAssociatedPromotions(Arrays.asList(new FixedPricePromotion(new BigDecimal("0.2"))));
            basketItem.setAssociatedPromotions(Arrays.asList(new FixedPricePromotion(BigDecimal.valueOf(0.2f))));
        }
    }

    public void addItem(Product product, int quantity) {
        BasketItem basketItem = productList.get(product.getId());
        if(basketItem != null) {
            basketItem.incQuantity(quantity);
        } else {
            BasketItem item = new BasketItem(product, quantity);
        }

        if(basketItem.getAssociatedPromotions().isEmpty()) {
//            basketItem.setAssociatedPromotions(Arrays.asList(new FixedPricePromotion(new BigDecimal("0.2"))));
            basketItem.setAssociatedPromotions(Arrays.asList(new FixedPricePromotion(BigDecimal.valueOf(0.2f))));
        }
    }

    public void remoteItem(Product product) {

    }

    public BigDecimal computeBasketCost() {
        BigDecimal basketPrice = BigDecimal.ZERO;
        for(BasketItem bi : productList.values()) {
            List<Promotion> associatedPromotions = bi.getAssociatedPromotions();
            BigDecimal q = BigDecimal.valueOf((long)bi.getQuantity());
            BigDecimal discountedPrice = bi.getProduct().getSellerPrice().multiply(q);
            for(Promotion p : associatedPromotions) {
                 discountedPrice = p.apply(discountedPrice, bi.getQuantity());
            }
            basketPrice = basketPrice.add(discountedPrice);
        }
        return basketPrice;
    }


    @Override public String toString() {
        return "Basket{" +
               "productList=" + productList + "\n"+
               "price after discount = " + computeBasketCost() + "\n"+
               ", basketOwner=" + basketOwner +
               '}';
    }
}
