package org.amex.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Orders {

    private long orderId;

    private final List<OrderLine> orderLineList;

    private final BigDecimal totalOrderCost;

    public Orders(final BigDecimal totalOrderCost, final List<OrderLine> orderLineList) {
        this.totalOrderCost = totalOrderCost;
        this.orderLineList = orderLineList;
    }

    public Orders(final long orderId, final BigDecimal totalOrderCost, final List<OrderLine> orderLineList) {
        this.orderId = orderId;
        this.totalOrderCost = totalOrderCost;
        this.orderLineList = orderLineList;
    }

    public List<OrderLine> getOrderLines() {
        return orderLineList;
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost;
    }

    public long getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return orderId == orders.orderId && Objects.equals(orderLineList, orders.orderLineList) && Objects.equals(totalOrderCost, orders.totalOrderCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderLineList, totalOrderCost);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", orderLineList=" + orderLineList +
                ", totalOrderCost=" + totalOrderCost +
                '}';
    }
}
