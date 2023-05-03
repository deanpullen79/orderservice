package org.amex.models.offers;

import org.amex.models.FruitProduct;
import org.amex.models.OrderLine;

import java.math.BigDecimal;
import java.util.List;

/**
 * An Offer strategy for Oranges, wherein there is no special offer
 */
public class OrangeNoOfferStrategy implements OfferStrategy {

    @Override
    public BigDecimal getOfferCost(final List<OrderLine> orderLineList) {

        return orderLineList.stream()
                .filter(o -> o.getFruitProduct().equals(FruitProduct.ORANGE))
                .map(OrderLine::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
