package org.amex.service;

import org.amex.models.OrderLine;
import org.amex.models.Orders;
import org.amex.models.offers.OfferStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final List<OfferStrategy> offerStrategies;

    public OrderService(@Autowired final List<OfferStrategy> offerStrategies) {
        this.offerStrategies = offerStrategies;
    }

    public Orders generateOrder(final List<OrderLine> orderLineList) {

        BigDecimal totalCost = BigDecimal.ZERO;
        for (final OfferStrategy offerStrategy : offerStrategies) {
            final BigDecimal offerCost = offerStrategy.getOfferCost(orderLineList);
            totalCost = totalCost.add(offerCost);
        }

        return new Orders(totalCost, List.copyOf(orderLineList));
    }
}
