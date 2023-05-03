package org.amex.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Order {

    private BigDecimal price;

    private FruitProduct fruitProduct;

    public Order() {
    }

    public Order(BigDecimal price, FruitProduct fruitProduct) {
        this.price = price;
        this.fruitProduct = fruitProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public FruitProduct getFruitProduct() {
        return fruitProduct;
    }

    public void setFruitProduct(FruitProduct fruitProduct) {
        this.fruitProduct = fruitProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(price, order.price) && fruitProduct == order.fruitProduct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, fruitProduct);
    }

    @Override
    public String toString() {
        return "{" +
                "price=" + price +
                ", fruitProduct=" + fruitProduct +
                '}';
    }
}
