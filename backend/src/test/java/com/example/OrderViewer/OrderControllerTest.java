package com.example.OrderViewer;

import com.example.OrderViewer.controller.OrderController;
import com.example.OrderViewer.entity.Order;
import com.example.OrderViewer.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Test
    void testGetOrders_returnsOk() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setCustomerName("Alice");

        Mockito.when(orderService.findOrders(any(), any(), any(), any(), any()))
                .thenReturn(List.of(order));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerName").value("Alice"));
    }

    @Test
    void testMarkAsPaid_returnsNoContent() throws Exception {
        mockMvc.perform(put("/api/orders/1/mark-paid"))
                .andExpect(status().isNoContent());

        Mockito.verify(orderService).markOrderAsPaid(1L);
    }
}

