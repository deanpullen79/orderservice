package org.amex.controllers;

import org.amex.controllers.requests.OrderRequest;
import org.amex.controllers.responses.GetOrdersResponse;
import org.amex.models.FruitProduct;
import org.amex.models.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderControllerTest {



    @Test
    public void testPostOrderReturnsCorrectSummaryAndTotalPrice() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderList.add(new Order(new BigDecimal("0.25"), FruitProduct.ORANGE));

        OrderRequest orderRequest = new OrderRequest(orderList);


        OrderController orderController = new OrderController();
        ResponseEntity<GetOrdersResponse> response = orderController.postOrder(orderRequest);

        GetOrdersResponse getOrdersResponse = response.getBody();
        List<Order> orderResponse = getOrdersResponse.getOrderList();

        assertEquals(2, orderResponse.size());

        Optional<Order> order = orderResponse.stream().filter(o -> o.getFruitProduct().equals(FruitProduct.APPLE)).findFirst();
        assertTrue(order.isPresent());
        assertEquals(order.get().getPrice(), new BigDecimal("0.60"));

        Optional<Order> order2 = orderResponse.stream().filter(o -> o.getFruitProduct().equals(FruitProduct.ORANGE)).findFirst();
        assertTrue(order2.isPresent());
        assertEquals(order2.get().getPrice(), new BigDecimal("0.25"));
    }
}