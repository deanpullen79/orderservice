package org.amex.repo;

import org.amex.models.Order;
import org.amex.models.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Pseudo repo, storing in a in-memory hashmap
 */
@Repository
public class OrderRepository {

    private final Map<Long, Order> orderRepo = new HashMap<>();

    private long orderId = 1;

    public Order saveOrder(final BigDecimal totalCost, final List<Product> products) {
        final Order order = new Order(orderId, totalCost, products);
        orderRepo.put(orderId, order);
        orderId++;

        return order;
    }

    public Order findOrder(final long orderId) {
        return orderRepo.get(orderId);
    }

    public Map<Long, Order> findOrders() {
        return orderRepo;
    }
}
