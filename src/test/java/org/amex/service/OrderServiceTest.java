package org.amex.service;

import org.amex.models.Order;
import org.amex.models.Product;
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

        List<Product> products = new ArrayList<>();
        products.add(Product.APPLE);
        products.add(Product.ORANGE);

        Order orderPassedIn = new Order(new BigDecimal("0.85"), products);
        when(orderRepository.saveOrder(new BigDecimal("0.85"), products)).thenReturn(orderPassedIn);

        Order order = orderService.createOrder(products);
        assertEquals(new BigDecimal("0.85"), order.getTotalOrderCost());
        assertEquals(products, order.getProducts());
    }

    @Test
    public void test_Order_Service_Offers() {
        List<OfferStrategy> offerStrategies = new ArrayList<>();
        offerStrategies.add(new AppleBogofOfferStrategy());
        offerStrategies.add(new Orange3For2OfferStrategy());

        OrderService orderService = new OrderService(orderRepository, offerStrategies);

        List<Product> products = new ArrayList<>();
        products.add(Product.APPLE);
        products.add(Product.APPLE);
        products.add(Product.ORANGE);
        products.add(Product.ORANGE);
        products.add(Product.ORANGE);

        Order order = new Order(new BigDecimal("1.10"), products);
        when(orderRepository.saveOrder(new BigDecimal("1.10"), products)).thenReturn(order);

        Order orderReturned = orderService.createOrder(products);
        assertEquals(new BigDecimal("1.10"), orderReturned.getTotalOrderCost());
        assertEquals(products, orderReturned.getProducts());
    }
}