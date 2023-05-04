package org.amex.service;

import org.amex.models.FruitProduct;
import org.amex.models.OrderLine;
import org.amex.models.Orders;
import org.amex.models.offers.*;
import org.amex.repo.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void test_Order_Service_No_Offers() {
        List<OfferStrategy> offerStrategies = new ArrayList<>();
        offerStrategies.add(new AppleNoOfferStrategy());
        offerStrategies.add(new OrangeNoOfferStrategy());

        OrderService orderService = new OrderService(orderRepository, offerStrategies);

        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));

        Orders ordersPassedIn = new Orders(new BigDecimal("0.85"), orderLineList);
        when(orderRepository.saveOrder(new BigDecimal("0.85"), orderLineList)).thenReturn(ordersPassedIn);

        Orders orders = orderService.createOrder(orderLineList);
        assertEquals(new BigDecimal("0.85"), orders.getTotalOrderCost());
        assertEquals(orderLineList, orders.getOrderLines());
    }

    @Test
    public void test_Order_Service_Offers() {
        List<OfferStrategy> offerStrategies = new ArrayList<>();
        offerStrategies.add(new AppleBogofOfferStrategy());
        offerStrategies.add(new Orange3For2OfferStrategy());

        OrderService orderService = new OrderService(orderRepository, offerStrategies);

        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));

        Orders orders = new Orders(new BigDecimal("1.10"), orderLineList);
        when(orderRepository.saveOrder(new BigDecimal("1.10"), orderLineList)).thenReturn(orders);

        Orders ordersReturned = orderService.createOrder(orderLineList);
        assertEquals(new BigDecimal("1.10"), ordersReturned.getTotalOrderCost());
        assertEquals(orderLineList, ordersReturned.getOrderLines());
    }
}