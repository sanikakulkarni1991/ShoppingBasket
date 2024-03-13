package org.example;

import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal price;
    private Offer offer;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, BigDecimal price, Offer offer) {
        this.name = name;
        this.price = price;
        this.offer = offer;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Offer getOffer() {
        return offer;
    }
}
