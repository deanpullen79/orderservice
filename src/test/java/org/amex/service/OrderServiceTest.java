package org.amex.service;

import org.amex.models.FruitProduct;
import org.amex.models.OrderLine;
import org.amex.models.Orders;
import org.amex.models.offers.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {

    @Test
    public void test_Order_Service_No_Offers() {
        List<OfferStrategy> offerStrategies = new ArrayList<>();
        offerStrategies.add(new AppleNoOfferStrategy());
        offerStrategies.add(new OrangeNoOfferStrategy());

        OrderService orderService = new OrderService(offerStrategies);

        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));

        Orders orders = orderService.generateOrder(orderLineList);
        assertEquals(new BigDecimal("0.85"), orders.getTotalOrderCost());
        assertEquals(orderLineList, orders.getOrderList());
    }

    @Test
    public void test_Order_Service_Offers() {
        List<OfferStrategy> offerStrategies = new ArrayList<>();
        offerStrategies.add(new AppleBogofOfferStrategy());
        offerStrategies.add(new Orange3For2OfferStrategy());

        OrderService orderService = new OrderService(offerStrategies);

        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));

        Orders orders = orderService.generateOrder(orderLineList);
        assertEquals(new BigDecimal("1.10"), orders.getTotalOrderCost());
        assertEquals(orderLineList, orders.getOrderList());
    }
}