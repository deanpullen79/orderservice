package org.amex.models.offers;

import org.amex.models.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * An Offer strategy for Oranges, wherein there is no special offer
 */
public class OrangeNoOfferStrategy implements OfferStrategy {

    @Override
    public BigDecimal getOfferCost(final List<Product> products) {

        return products.stream()
                .filter(o -> o.equals(Product.ORANGE))
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
