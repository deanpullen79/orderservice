package org.amex.controllers.responses;


import org.amex.models.Product;

import java.math.BigDecimal;
import java.util.List;

public class GetOrdersResponse {

    private BigDecimal totalOrderCost;

    private List<Product> products;

    public GetOrdersResponse() {
    }

    public GetOrdersResponse(final BigDecimal totalOrderCost, final List<Product> products) {
        this.totalOrderCost = totalOrderCost;
        this.products = products;
    }

    public List<Product> getProducts() {
        // Preserve immutability
        return List.copyOf(products);
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost;
    }

}
