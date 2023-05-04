package org.amex.repo;

import org.amex.models.OrderLine;
import org.amex.models.Orders;
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

    private final Map<Long, Orders> orderRepo = new HashMap<>();

    private long orderId = 1;

    public Orders saveOrder(final BigDecimal totalCost, final List<OrderLine> orderLineList) {
        final Orders order = new Orders(orderId, totalCost, orderLineList);
        orderRepo.put(orderId, order);
        orderId++;

        return order;
    }

    public Orders findOrder(final long orderId) {
        return orderRepo.get(orderId);
    }

    public Map<Long, Orders> findOrders() {
        return orderRepo;
    }
}
