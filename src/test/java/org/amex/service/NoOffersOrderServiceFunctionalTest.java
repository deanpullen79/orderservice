package org.amex.service;

import org.amex.models.Order;
import org.amex.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("offers")
public class NoOffersOrderServiceFunctionalTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void test_Order_Service_With_No_Offers() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);

        Order order = orderService.createOrder(orderLineList);
        assertEquals(new BigDecimal("1.10"), order.getTotalOrderCost());
        assertEquals(orderLineList, order.getProducts());
    }
}