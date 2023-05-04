package org.amex.controllers;

import org.amex.controllers.requests.OrderRequest;
import org.amex.controllers.responses.GetAllOrdersResponse;
import org.amex.controllers.responses.GetOrdersResponse;
import org.amex.models.Order;
import org.amex.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(@Autowired OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Restful API interface to post an order to the system, via a collection of Order's
     * We only represent the happy path for the sake of the demo
     *
     * @param orderRequest the underlying json body comprising a list of Order's
     * @return the summary of the order in json body
     */
    @PostMapping(path = "/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetOrdersResponse> postOrder(final @RequestBody OrderRequest orderRequest) {

        final Order order = orderService.createOrder(orderRequest.getProducts());
        final GetOrdersResponse response = new GetOrdersResponse(order.getTotalOrderCost(), order.getProducts());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Restful API interface to return a stored order ID
     *
     * @param orderId the order ID to find in the 'database'
     * @return the Order lines and total cost
     */
    @GetMapping(path = "/order/{orderId}")
    public ResponseEntity<GetOrdersResponse> getOrder(final @PathVariable long orderId) {

        final Order order = orderService.getOrder(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        final GetOrdersResponse response = new GetOrdersResponse(order.getTotalOrderCost(), order.getProducts());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Restful API interface to return all stored orders
     *
     * @return the Orders, with Order Lines and total cost
     */
    @GetMapping(path = "/orders")
    public ResponseEntity<GetAllOrdersResponse> getAllOrders() {

        final Map<Long, Order> orders = orderService.getAllOrders();

        final GetAllOrdersResponse response = new GetAllOrdersResponse(orders);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
