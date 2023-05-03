package org.amex.controllers;

import org.amex.controllers.requests.OrderRequest;
import org.amex.controllers.responses.GetOrdersResponse;
import org.amex.models.Orders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {


    /**
     * Restful API interface to post an order to the system, via a collection of Order's
     *
     * We only represent the happy path for the sake of the demo
     *
     * @param orderRequest the underlying json body comprising a list of Order's
     * @return the summary of the order in json body
     */
    @PostMapping(path = "/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetOrdersResponse> postOrder(final @RequestBody OrderRequest orderRequest) {

        // Usually you'd do something here like persisting to database etc, via some impl of service and repo classes
        // However for simplicity of demo I've added the 'logic' here and we simply use the objects passed in for the summary.


        final Orders orders = new Orders(orderRequest.getOrderList());
        final GetOrdersResponse response = new GetOrdersResponse(orders.getTotalOrderCost(), orders.getOrderList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
