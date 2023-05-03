package org.amex.models.offers;

import org.amex.models.FruitProduct;
import org.amex.models.OrderLine;

import java.math.BigDecimal;
import java.util.List;

/**
 * An Offer strategy for Apples, wherein you buy one get one free
 */
public class AppleBogofOfferStrategy implements OfferStrategy {

    @Override
    public BigDecimal getOfferCost(final List<OrderLine> orderLineList) {

        // Get apples only and store price for each in a list
        List<BigDecimal> orderLines = orderLineList.stream()
                .filter(o -> o.getFruitProduct().equals(FruitProduct.APPLE))
                .map(OrderLine::getPrice)
                .toList();

        // Only store where i is odd price, to achieve Bogof
        BigDecimal totalCost = BigDecimal.ZERO;
        for (int i = 0; i < orderLines.size(); i++) {
            if (!(i != 0 && ((i + 1) % 2 == 0))) {
                totalCost = totalCost.add(orderLines.get(i));
            }
        }

        return totalCost;
    }
}
