package org.amex.models.offers;

import org.amex.models.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * An Offer strategy for Apples, wherein there is no special offer
 */
public class AppleNoOfferStrategy implements OfferStrategy {

    @Override
    public BigDecimal getOfferCost(final List<Product> products) {

        return products.stream()
                .filter(o -> o.equals(Product.APPLE))
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
