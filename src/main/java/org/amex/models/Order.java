package org.amex.models;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private long orderId;

    private final List<Product> productList;

    private final BigDecimal totalOrderCost;

    public Order(final BigDecimal totalOrderCost, final List<Product> productList) {
        this.totalOrderCost = totalOrderCost;
        this.productList = productList;
    }

    public Order(final long orderId, final BigDecimal totalOrderCost, final List<Product> productList) {
        this.orderId = orderId;
        this.totalOrderCost = totalOrderCost;
        this.productList = productList;
    }

    public List<Product> getProducts() {
        return productList;
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost;
    }

    public long getOrderId() {
        return orderId;
    }


}
