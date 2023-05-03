package org.amex.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Orders {

    private final List<OrderLine> orderLineList;

    private BigDecimal totalOrderCost;

    public Orders(final BigDecimal totalOrderCost, final List<OrderLine> orderLineList) {
        this.totalOrderCost = totalOrderCost;
        this.orderLineList = orderLineList;
    }

    public List<OrderLine> getOrderList() {
        return orderLineList;
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(orderLineList, orders.orderLineList) && Objects.equals(totalOrderCost, orders.totalOrderCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderLineList, totalOrderCost);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderLineList=" + orderLineList +
                ", totalOrderCost=" + totalOrderCost +
                '}';
    }
}
