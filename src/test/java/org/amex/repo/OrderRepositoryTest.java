package org.amex.repo;

import org.amex.models.FruitProduct;
import org.amex.models.OrderLine;
import org.amex.models.Orders;
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
        List<OrderLine> orderLineList = getOrderLines();

        OrderRepository orderRepository = new OrderRepository();
        Orders orders = orderRepository.saveOrder(new BigDecimal("1.95"), orderLineList);

        assertEquals(1, orders.getOrderId());
        assertEquals(orderLineList, orders.getOrderLines());
    }

    @Test
    public void findOrder() {
        List<OrderLine> orderLineList = getOrderLines();

        OrderRepository orderRepository = new OrderRepository();
        Orders orders = orderRepository.saveOrder(new BigDecimal("1.95"), orderLineList);

        Orders ordersReturn = orderRepository.findOrder(orders.getOrderId());
        assertEquals(orderLineList, ordersReturn.getOrderLines());
        assertEquals(1, ordersReturn.getOrderId());
    }

    @Test
    public void find_No_Order() {
        OrderRepository orderRepository = new OrderRepository();
        Orders orders = orderRepository.findOrders().get(1000L);

        assertNull(orders);
    }

    @Test
    public void findOrders() {
        List<OrderLine> orderLineList = getOrderLines();

        OrderRepository orderRepository = new OrderRepository();
        Orders orders1 = orderRepository.saveOrder(new BigDecimal("1.95"), orderLineList);
        Orders orders2 = orderRepository.saveOrder(new BigDecimal("2.95"), orderLineList);

        Map<Long, Orders> allOrders = orderRepository.findOrders();

        assertEquals(orders1, allOrders.get(orders1.getOrderId()));
        assertEquals(orders2, allOrders.get(orders2.getOrderId()));
    }

    private static List<OrderLine> getOrderLines() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        return orderLineList;
    }

}