package org.amex.controllers;

import org.amex.controllers.requests.OrderRequest;
import org.amex.controllers.responses.GetOrdersResponse;
import org.amex.models.FruitProduct;
import org.amex.models.OrderLine;
import org.amex.models.offers.*;
import org.amex.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderLineControllerTest {

    @Test
    public void test_PostOrder_Returns_CorrectSummary_And_TotalPrice() {
        List<OfferStrategy> offerStrategies = new ArrayList<>();
        offerStrategies.add(new AppleNoOfferStrategy());
        offerStrategies.add(new OrangeNoOfferStrategy());
        OrderController orderController = new OrderController(new OrderService(offerStrategies));


        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));

        OrderRequest orderRequest = new OrderRequest(orderLineList);


        ResponseEntity<GetOrdersResponse> response = orderController.postOrder(orderRequest);

        GetOrdersResponse getOrdersResponse = response.getBody();
        List<OrderLine> orderLineResponse = getOrdersResponse.getOrderList();

        assertEquals(2, orderLineResponse.size());

        Optional<OrderLine> order = orderLineResponse.stream().filter(o -> o.getFruitProduct().equals(FruitProduct.APPLE)).findFirst();
        assertTrue(order.isPresent());
        assertEquals(order.get().getPrice(), new BigDecimal("0.60"));

        Optional<OrderLine> order2 = orderLineResponse.stream().filter(o -> o.getFruitProduct().equals(FruitProduct.ORANGE)).findFirst();
        assertTrue(order2.isPresent());
        assertEquals(order2.get().getPrice(), new BigDecimal("0.25"));

        assertEquals(getOrdersResponse.getTotalOrderCost(), new BigDecimal("0.85"));
    }

    @Test
    public void test_PostOrder_Returns_CorrectSummary_And_TotalPrice_With_Offers() {
        List<OfferStrategy> offerStrategies = new ArrayList<>();
        offerStrategies.add(new AppleBogofOfferStrategy());
        offerStrategies.add(new Orange3For2OfferStrategy());
        OrderController orderController = new OrderController(new OrderService(offerStrategies));


        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));

        OrderRequest orderRequest = new OrderRequest(orderLineList);


        ResponseEntity<GetOrdersResponse> response = orderController.postOrder(orderRequest);

        GetOrdersResponse getOrdersResponse = response.getBody();
        List<OrderLine> orderLineResponse = getOrdersResponse.getOrderList();

        assertEquals(5, orderLineResponse.size());

        assertEquals(getOrdersResponse.getTotalOrderCost(), new BigDecimal("1.10"));
    }
}