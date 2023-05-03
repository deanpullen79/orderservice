package org.amex.service;

import org.amex.models.FruitProduct;
import org.amex.models.OrderLine;
import org.amex.models.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("no-offers")
public class OffersOrderServiceFunctionalTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void test_Order_Service_With_Offers() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));

        Orders orders = orderService.generateOrder(orderLineList);
        assertEquals(new BigDecimal("1.95"), orders.getTotalOrderCost());
        assertEquals(orderLineList, orders.getOrderList());
    }
}