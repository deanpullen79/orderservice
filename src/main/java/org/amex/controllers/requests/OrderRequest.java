package org.amex.controllers.requests;

import org.amex.models.Order;

import java.util.List;

public class OrderRequest {

    private List<Order> orderList;

    public OrderRequest() {
    }

    public OrderRequest(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
