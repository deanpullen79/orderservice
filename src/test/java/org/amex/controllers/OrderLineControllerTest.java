package org.amex.controllers;

import org.amex.controllers.requests.OrderRequest;
import org.amex.controllers.responses.GetOrdersResponse;
import org.amex.models.Order;
import org.amex.models.Product;
import org.amex.models.offers.*;
import org.amex.repo.OrderRepository;
import org.amex.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderLineControllerTest {

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void test_PostOrder_Returns_CorrectSummary_And_TotalPrice() {
        List<OfferStrategy> offerStrategies = new ArrayList<>();
        offerStrategies.add(new AppleNoOfferStrategy());
        offerStrategies.add(new OrangeNoOfferStrategy());
        OrderController orderController = new OrderController(new OrderService(orderRepository, offerStrategies));


        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.ORANGE);

        OrderRequest orderRequest = new OrderRequest(orderLineList);

        when(orderRepository.saveOrder(new BigDecimal("0.85"), orderLineList)).thenReturn(new Order(1000, new BigDecimal("0.85"), orderLineList));

        ResponseEntity<GetOrdersResponse> response = orderController.postOrder(orderRequest);

        GetOrdersResponse getOrdersResponse = response.getBody();
        List<Product> orderLineResponse = getOrdersResponse.getProducts();

        assertEquals(2, orderLineResponse.size());

        Optional<Product> order = orderLineResponse.stream().filter(o -> o.equals(Product.APPLE)).findFirst();
        assertTrue(order.isPresent());
        assertEquals(order.get().getPrice(), new BigDecimal("0.60"));

        Optional<Product> order2 = orderLineResponse.stream().filter(o -> o.equals(Product.ORANGE)).findFirst();
        assertTrue(order2.isPresent());
        assertEquals(order2.get().getPrice(), new BigDecimal("0.25"));

        assertEquals(getOrdersResponse.getTotalOrderCost(), new BigDecimal("0.85"));
    }

    @Test
    public void test_PostOrder_Returns_CorrectSummary_And_TotalPrice_With_Offers() {
        List<OfferStrategy> offerStrategies = new ArrayList<>();
        offerStrategies.add(new AppleBogofOfferStrategy());
        offerStrategies.add(new Orange3For2OfferStrategy());
        OrderController orderController = new OrderController(new OrderService(orderRepository, offerStrategies));


        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);

        OrderRequest orderRequest = new OrderRequest(orderLineList);

        when(orderRepository.saveOrder(new BigDecimal("1.10"), orderLineList)).thenReturn(new Order(1000, new BigDecimal("1.10"), orderLineList));

        ResponseEntity<GetOrdersResponse> response = orderController.postOrder(orderRequest);

        GetOrdersResponse getOrdersResponse = response.getBody();
        List<Product> orderLineResponse = getOrdersResponse.getProducts();

        assertEquals(5, orderLineResponse.size());

        assertEquals(getOrdersResponse.getTotalOrderCost(), new BigDecimal("1.10"));
    }

    @Test
    public void test_GetOrder_Returns_Order() {
        List<OfferStrategy> offerStrategies = new ArrayList<>();
        offerStrategies.add(new AppleBogofOfferStrategy());
        offerStrategies.add(new Orange3For2OfferStrategy());
        OrderController orderController = new OrderController(new OrderService(orderRepository, offerStrategies));


        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);
        orderLineList.add(Product.ORANGE);

        when(orderRepository.findOrder(1000)).thenReturn(new Order(1000, new BigDecimal("1.10"), orderLineList));

        ResponseEntity<GetOrdersResponse> response = orderController.getOrder(1000);

        GetOrdersResponse getOrdersResponse = response.getBody();
        List<Product> orderLineResponse = getOrdersResponse.getProducts();

        assertEquals(5, orderLineResponse.size());

        assertEquals(getOrdersResponse.getTotalOrderCost(), new BigDecimal("1.10"));
    }

    @Test
    public void test_GetOrder_Returns_No_Order() {
        List<OfferStrategy> offerStrategies = new ArrayList<>();
        offerStrategies.add(new AppleBogofOfferStrategy());
        offerStrategies.add(new Orange3For2OfferStrategy());
        OrderController orderController = new OrderController(new OrderService(orderRepository, offerStrategies));

        when(orderRepository.findOrder(1000)).thenReturn(null);

        ResponseEntity<GetOrdersResponse> response = orderController.getOrder(1000);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}