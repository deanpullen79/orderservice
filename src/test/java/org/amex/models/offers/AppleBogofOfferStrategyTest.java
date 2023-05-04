package org.amex.models.offers;

import org.amex.models.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppleBogofOfferStrategyTest {

    private final AppleBogofOfferStrategy appleBogofOfferStrategy = new AppleBogofOfferStrategy();

    @Test
    public void test_Get_Offer_Cost_For_AppleBogof_with_1_Apple() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.60"), result);
    }
    
    @Test
    public void test_Get_Offer_Cost_For_AppleBogof_with_2_Apples() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.APPLE);

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.60"), result);
    }

    @Test
    public void test_Get_Offer_Cost_For_AppleBogof_with_3_Apples() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.APPLE);

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("1.20"), result);
    }
    

    @Test
    public void test_Get_Offer_Cost_For_AppleBogof_with_3_Oranges_1_Apple() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.APPLE);

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.60"), result);
    }

    @Test
    public void test_Get_Offer_Cost_For_AppleBogof_with_1_Orange() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.ORANGE);

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(BigDecimal.ZERO, result);
    }


    @Test
    public void test_Get_Offer_Cost_For_AppleBogof_with_2_Apples_1_Orange() {
        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.ORANGE);

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(new BigDecimal("0.60"), result);
    }

    @Test
    public void test_getOfferCost_with_empty_orders() {
        List<Product> orderLineList = new ArrayList<>();

        BigDecimal result = appleBogofOfferStrategy.getOfferCost(orderLineList);

        assertEquals(BigDecimal.ZERO, result);
    }
}