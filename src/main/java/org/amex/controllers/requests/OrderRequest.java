package org.amex.controllers.requests;

import org.amex.models.Product;

import java.util.List;

public class OrderRequest {

    private List<Product> products;

    public OrderRequest() {
    }

    public OrderRequest(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setOrderList(List<Product> products) {
        this.products = products;
    }
}
