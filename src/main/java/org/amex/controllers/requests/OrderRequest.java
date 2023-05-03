package org.amex.controllers.requests;

import org.amex.models.OrderLine;

import java.util.List;

public class OrderRequest {

    private List<OrderLine> orderLineList;

    public OrderRequest() {
    }

    public OrderRequest(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }

    public List<OrderLine> getOrderList() {
        return orderLineList;
    }

    public void setOrderList(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }
}
