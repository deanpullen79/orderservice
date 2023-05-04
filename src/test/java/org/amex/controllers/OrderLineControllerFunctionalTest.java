package org.amex.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.amex.controllers.requests.OrderRequest;
import org.amex.models.Order;
import org.amex.models.Product;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void test_Post_Order_Returns_Correct_Summary_And_Total_Price() throws Exception {
        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new ToStringSerializer());
        objectMapper.registerModule(module);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.ORANGE);


        Order order = new Order(new BigDecimal("0.85"), orderLineList);
        when(orderService.createOrder(orderLineList)).thenReturn(order);


        OrderRequest orderRequest = new OrderRequest(orderLineList);
        this.mockMvc.perform(
                        post("/orders/order")
                                .content(asJsonString(orderRequest))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE))
//{"totalOrderCost":"0.85","products":["Product{name='Apple', price=0.60}","Product{name='Orange', price=0.25}"]}
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalOrderCost").value("0.85"))
                .andExpect(jsonPath("$.products[0]").value("Product{name='Apple', price=0.60}"))
                .andExpect(jsonPath("$.products[1]").value("Product{name='Orange', price=0.25}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
    }

    @Test
    public void test_Get_Order_Returns_Correctly() throws Exception {
        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new ToStringSerializer());
        objectMapper.registerModule(module);

        List<Product> orderLineList = new ArrayList<>();
        orderLineList.add(Product.APPLE);
        orderLineList.add(Product.ORANGE);


        Order order = new Order(new BigDecimal("0.85"), orderLineList);
        when(orderService.getOrder(1000)).thenReturn(order);


        this.mockMvc.perform(
                        get("/orders/order/1000")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalOrderCost").value("0.85"))
                .andExpect(jsonPath("$.products[0]").value("Product{name='Apple', price=0.60}"))
                .andExpect(jsonPath("$.products[1]").value("Product{name='Orange', price=0.25}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
    }

    @Test
    public void test_Get_Order_Returns_Nothing_When_No_Order_Exists() throws Exception {
        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new ToStringSerializer());
        objectMapper.registerModule(module);


        when(orderService.getOrder(1000)).thenReturn(null);


        this.mockMvc.perform(
                        get("/orders/order/1000")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
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