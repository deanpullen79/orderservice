package org.amex.controllers.responses;

import org.amex.models.OrderLine;

import java.math.BigDecimal;
import java.util.List;

public class GetOrdersResponse {

    private BigDecimal totalOrderCost;

    private List<OrderLine> orderLineList;

    public GetOrdersResponse() {
    }

    public GetOrdersResponse(final BigDecimal totalOrderCost, final List<OrderLine> orderLineList) {
        this.totalOrderCost = totalOrderCost;
        this.orderLineList = orderLineList;
    }

    public List<OrderLine> getOrderList() {
        // Preserve immutability
        return List.copyOf(orderLineList);
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost;
    }

}
