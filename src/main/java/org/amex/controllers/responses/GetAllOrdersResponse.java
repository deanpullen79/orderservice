package org.amex.controllers.responses;

import org.amex.models.Order;

import java.util.Map;

public class GetAllOrdersResponse {

    private Map<Long, Order> allOrders;

    public GetAllOrdersResponse() {
    }

    public GetAllOrdersResponse(final Map<Long, Order> allOrders) {
        this.allOrders = allOrders;
    }

    public Map<Long, Order> getAllOrders() {
        return allOrders;
    }
}
