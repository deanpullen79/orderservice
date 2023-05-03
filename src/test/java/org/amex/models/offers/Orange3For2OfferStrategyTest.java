package org.amex.models.offers;

import org.amex.models.FruitProduct;
import org.amex.models.OrderLine;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Orange3For2OfferStrategyTest {

    private final Orange3For2OfferStrategy orange3For2OfferStrategy = new Orange3For2OfferStrategy();

    @Test
    public void test_getOfferCost_forOrange3for2_with_2_Oranges() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));

        BigDecimal result = orange3For2OfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.85"), result);
    }

    @Test
    public void test_getOfferCost_forOrange3for2_with_3_Oranges() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));

        BigDecimal result = orange3For2OfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.50"), result);
    }

    @Test
    public void test_getOfferCost_forOrange3for2_with_3_Oranges_1_Apple() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));

        BigDecimal result = orange3For2OfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.50"), result);
    }

    @Test
    public void test_getOfferCost_forOrange3for2_with_1_Apple() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));

        BigDecimal result = orange3For2OfferStrategy.getOfferCost(orderLineList);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void test_getOfferCost_forOrange3for2_with_2_Apples_1_Orange() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));

        BigDecimal result = orange3For2OfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.25"), result);
    }

    @Test
    public void test_getOfferCost_with_empty_orders() {
        List<OrderLine> orderLineList = new ArrayList<>();

        BigDecimal result = orange3For2OfferStrategy.getOfferCost(orderLineList);

        assertEquals(BigDecimal.ZERO, result);
    }
}