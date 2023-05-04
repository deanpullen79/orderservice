package org.amex.models;

import java.math.BigDecimal;

public enum Product {

    APPLE("Apple", new BigDecimal("0.60")),
    ORANGE("Orange", new BigDecimal("0.25"));

    private final String name;

    private final BigDecimal price;

    Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
