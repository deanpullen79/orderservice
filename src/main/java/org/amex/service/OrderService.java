package org.amex.service;

import org.amex.models.OrderLine;
import org.amex.models.Orders;
import org.amex.models.offers.OfferStrategy;
import org.amex.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final List<OfferStrategy> offerStrategies;


    public OrderService(OrderRepository orderRepository, List<OfferStrategy> offerStrategies) {
        this.orderRepository = orderRepository;
        this.offerStrategies = offerStrategies;
    }

    public Orders createOrder(final List<OrderLine> orderLineList) {

        BigDecimal totalCost = BigDecimal.ZERO;
        for (final OfferStrategy offerStrategy : offerStrategies) {
            final BigDecimal offerCost = offerStrategy.getOfferCost(orderLineList);
            totalCost = totalCost.add(offerCost);
        }

        return orderRepository.saveOrder(totalCost, List.copyOf(orderLineList));
    }

    public Orders getOrder(final long orderId) {
        return orderRepository.findOrder(orderId);
    }

    public Map<Long, Orders> getAllOrders() {
        return orderRepository.findOrders();
    }
}
