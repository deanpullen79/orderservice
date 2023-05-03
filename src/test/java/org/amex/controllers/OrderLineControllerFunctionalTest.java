package org.amex.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.amex.controllers.requests.OrderRequest;
import org.amex.models.FruitProduct;
import org.amex.models.OrderLine;
import org.amex.models.Orders;
import org.amex.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("offers")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OrderController.class)
public class OrderLineControllerFunctionalTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;


    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void test_Post_Order_Returns_Correct_Summary_And_Total_Price() throws Exception {

        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new ToStringSerializer());
        objectMapper.registerModule(module);

        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(new BigDecimal("0.60"), FruitProduct.APPLE));
        orderLineList.add(new OrderLine(new BigDecimal("0.25"), FruitProduct.ORANGE));


        Orders orders = new Orders(new BigDecimal("0.85"), orderLineList);
        when(orderService.generateOrder(orderLineList)).thenReturn(orders);


        OrderRequest orderRequest = new OrderRequest(orderLineList);
        this.mockMvc.perform(
                        post("/orders/order")
                                .content(asJsonString(orderRequest))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalOrderCost").value("0.85"))
                .andExpect(jsonPath("$.orderList[0].price").value("0.60"))
                .andExpect(jsonPath("$.orderList[0].fruitProduct").value("APPLE"))
                .andExpect(jsonPath("$.orderList[1].price").value("0.25"))
                .andExpect(jsonPath("$.orderList[1].fruitProduct").value("ORANGE"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
    }


    private String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}