package org.amex.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Orders {

    private final List<Order> orderList;

    public Orders(final List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public BigDecimal getTotalOrderCost() {
        return orderList.stream().map(Order::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(orderList, orders.orderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderList);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderList=" + orderList +
                '}';
    }
}
