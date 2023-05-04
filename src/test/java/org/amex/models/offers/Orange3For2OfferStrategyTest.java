package org.amex.models.offers;

import org.amex.models.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Orange3For2OfferStrategyTest {

    private final Orange3For2OfferStrategy orange3For2OfferStrategy = new Orange3For2OfferStrategy();

    @Test
    public void test_getOfferCost_forOrange3for2_with_2_Oranges() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);

        BigDecimal result = orange3For2OfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.50"), result);
    }

    @Test
    public void test_getOfferCost_forOrange3for2_with_3_Oranges() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);

        BigDecimal result = orange3For2OfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.50"), result);
    }

    @Test
    public void test_getOfferCost_forOrange3for2_with_3_Oranges_1_Apple() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.APPLE);

        BigDecimal result = orange3For2OfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.50"), result);
    }

    @Test
    public void test_getOfferCost_forOrange3for2_with_1_Apple() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);

        BigDecimal result = orange3For2OfferStrategy.getOfferCost(orderLineList);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void test_getOfferCost_forOrange3for2_with_2_Apples_1_Orange() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.ORANGE);

        BigDecimal result = orange3For2OfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.25"), result);
    }

    @Test
    public void test_getOfferCost_with_empty_orders() {
        List<Product> orderLineList = new ArrayList<>();

        BigDecimal result = orange3For2OfferStrategy.getOfferCost(orderLineList);

        assertEquals(BigDecimal.ZERO, result);
    }
}