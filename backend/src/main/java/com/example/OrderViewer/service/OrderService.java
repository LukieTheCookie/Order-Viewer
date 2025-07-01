package com.example.OrderViewer.service;

import com.example.OrderViewer.entity.Order;
import com.example.OrderViewer.entity.OrderStatus;
import com.example.OrderViewer.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found")));
    }

    public Order markOrderAsPaid(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setPaid(true);
        return orderRepository.save(order);
    }

    public List<Order> findOrders(
            OrderStatus status,
            LocalDateTime createdFrom,
            LocalDateTime createdTo,
            Double totalMin,
            Double totalMax) {

        Specification<Order> spec = (root, query, cb) -> cb.conjunction();

        if (status != null) {
            spec = spec.and(OrderSpecification.hasStatus(status));
        }
        if (createdFrom != null) {
            spec = spec.and(OrderSpecification.createdFrom(createdFrom));
        }
        if (createdTo != null) {
            spec = spec.and(OrderSpecification.createdTo(createdTo));
        }
        if (totalMin != null) {
            spec = spec.and(OrderSpecification.totalMin(totalMin));
        }
        if (totalMax != null) {
            spec = spec.and(OrderSpecification.totalMax(totalMax));
        }

        return orderRepository.findAll(spec);
    }

    public Statistics calculateStatistics(
            OrderStatus status,
            LocalDateTime createdFrom,
            LocalDateTime createdTo,
            Double totalMin,
            Double totalMax) {

        List<Order> filteredOrders = findOrders(status, createdFrom, createdTo, totalMin, totalMax);

        long count = filteredOrders.size();
        double grandTotal = filteredOrders.stream()
                .mapToDouble(Order::getTotal)
                .sum();

        return new Statistics(count, grandTotal);
    }


    public static class Statistics {
        private long count;
        private double grandTotal;

        public Statistics(long count, double grandTotal) {
            this.count = count;
            this.grandTotal = grandTotal;
        }

        public long getCount() { return count; }
        public void setCount(long count) { this.count = count; }
        public double getGrandTotal() { return grandTotal; }
        public void setGrandTotal(double grandTotal) { this.grandTotal = grandTotal; }
    }
}
