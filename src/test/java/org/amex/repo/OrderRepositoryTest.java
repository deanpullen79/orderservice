package org.amex.repo;

import org.amex.models.Order;
import org.amex.models.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderRepositoryTest {

    @Test
    public void saveOrder() {
        List<Product> orderLineList = getOrderLines();

        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.saveOrder(new BigDecimal("1.95"), orderLineList);

        assertEquals(1, order.getOrderId());
        assertEquals(orderLineList, order.getProducts());
    }

    @Test
    public void findOrder() {
        List<Product> orderLineList = getOrderLines();

        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.saveOrder(new BigDecimal("1.95"), orderLineList);

        Order orderReturn = orderRepository.findOrder(order.getOrderId());
        assertEquals(orderLineList, orderReturn.getProducts());
        assertEquals(1, orderReturn.getOrderId());
    }

    @Test
    public void find_No_Order() {
        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.findOrders().get(1000L);

        assertNull(order);
    }

    @Test
    public void findOrders() {
        List<Product> orderLineList = getOrderLines();

        OrderRepository orderRepository = new OrderRepository();
        Order order1 = orderRepository.saveOrder(new BigDecimal("1.95"), orderLineList);
        Order order2 = orderRepository.saveOrder(new BigDecimal("2.95"), orderLineList);

        Map<Long, Order> allOrders = orderRepository.findOrders();

        assertEquals(order1, allOrders.get(order1.getOrderId()));
        assertEquals(order2, allOrders.get(order2.getOrderId()));
    }

    private static List<Product> getOrderLines() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);
        return orderLineList;
    }

}