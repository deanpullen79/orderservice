package org.amex.models.offers;

import org.amex.models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface OfferStrategy {

    BigDecimal getOfferCost(final List<Product> products);
}
