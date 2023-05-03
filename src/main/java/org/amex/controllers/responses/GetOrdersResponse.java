package org.amex.controllers.responses;

import org.amex.models.Order;

import java.math.BigDecimal;
import java.util.List;

public class GetOrdersResponse {

    private BigDecimal totalOrderCost;

    private List<Order> orderList;

    public GetOrdersResponse() {
    }

    public GetOrdersResponse(final BigDecimal totalOrderCost, final List<Order> orderList) {
        this.totalOrderCost = totalOrderCost;
        this.orderList = orderList;
    }

    public List<Order> getOrderList() {
        // Preserve immutability
        return List.copyOf(orderList);
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost;
    }

}
