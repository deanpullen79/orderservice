package org.amex.models.offers;

import org.amex.models.FruitProduct;
import org.amex.models.OrderLine;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppleBogofOfferStrategyTest {

    private final AppleBogofOfferStrategy appleBogofOfferStrategy = new AppleBogofOfferStrategy();

    @Test
    public void test_Get_Offer_Cost_For_AppleBogof_with_1_Apple() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.60"), result);
    }
    
    @Test
    public void test_Get_Offer_Cost_For_AppleBogof_with_2_Apples() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.60"), result);
    }

    @Test
    public void test_Get_Offer_Cost_For_AppleBogof_with_3_Apples() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("1.20"), result);
    }
    

    @Test
    public void test_Get_Offer_Cost_For_AppleBogof_with_3_Oranges_1_Apple() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.60"), result);
    }

    @Test
    public void test_Get_Offer_Cost_For_AppleBogof_with_1_Orange() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(BigDecimal.ZERO, result);
    }


    @Test
    public void test_Get_Offer_Cost_For_AppleBogof_with_2_Apples_1_Orange() {
        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.60"), result);
    }

    @Test
    public void test_getOfferCost_with_empty_orders() {
        List<OrderLine> orderLineList = new ArrayList<>();

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(BigDecimal.ZERO, result);
    }
}