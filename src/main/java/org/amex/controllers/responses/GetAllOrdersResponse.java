package org.amex.controllers.responses;

import org.amex.models.Orders;

import java.util.Map;

public class GetAllOrdersResponse {

    private Map<Long, Orders> allOrders;

    public GetAllOrdersResponse() {
    }

    public GetAllOrdersResponse(final Map<Long, Orders>allOrders) {
        this.allOrders = allOrders;
    }

    public Map<Long, Orders> getAllOrders() {
        return allOrders;
    }
}
