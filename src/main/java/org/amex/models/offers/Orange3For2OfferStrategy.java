package org.amex.models.offers;

import org.amex.models.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * An Offer strategy for Oranges, wherein you get 3 for the price of 2
 */
public class Orange3For2OfferStrategy implements OfferStrategy {

    @Override
    public BigDecimal getOfferCost(final List<Product> products) {

        // Get apples only and store price for each in a list
        List<BigDecimal> orderLines = products.stream()
                .filter(o -> o.equals(Product.ORANGE))
                .map(Product::getPrice)
                .toList();

        // Only charge for the first two of three
        BigDecimal totalCost = BigDecimal.ZERO;
        for (int i = 0; i < orderLines.size(); i++) {
            if (!(i != 0 && ((i + 1) % 3 == 0))) {
                totalCost = totalCost.add(orderLines.get(i));
            }
        }

        return totalCost;
    }
}
