package com.example.OrderViewer;

import com.example.OrderViewer.entity.Order;
import com.example.OrderViewer.entity.OrderStatus;
import com.example.OrderViewer.repository.OrderRepository;
import com.example.OrderViewer.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;

    @Test
    void testFindOrders_returnsMatchingOrders() {
        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);
        Mockito.when(orderRepository.findAll(any(Specification.class)))
                .thenReturn(List.of(order));

        List<Order> result = orderService.findOrders(
                OrderStatus.PENDING, null, null, null, null
        );

        assertEquals(1, result.size());
        assertEquals(OrderStatus.PENDING, result.get(0).getStatus());
    }

    @Test
    void testMarkAsPaid_setsPaidTrue() {
        Order order = new Order();
        order.setId(1L);
        order.setPaid(false);

        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        orderService.markOrderAsPaid(1L);

        assertTrue(order.isPaid());
        Mockito.verify(orderRepository).save(order);
    }

    @Test
    void testFindById_notFound() {
        Mockito.when(orderRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            orderService.findById(999L);
        });
    }
}

