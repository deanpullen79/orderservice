package org.amex.models;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderLine {

    private BigDecimal price;

    private FruitProduct fruitProduct;

    public OrderLine() {
    }

    public OrderLine(BigDecimal price, FruitProduct fruitProduct) {
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
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(price, orderLine.price) && fruitProduct == orderLine.fruitProduct;
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
