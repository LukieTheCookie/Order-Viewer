package com.example.OrderViewer.seeder;

import com.example.OrderViewer.entity.Order;
import com.example.OrderViewer.entity.OrderItem;
import com.example.OrderViewer.entity.OrderStatus;
import com.example.OrderViewer.repository.OrderItemRepository;
import com.example.OrderViewer.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final Random random = new Random();

    private static final List<String> CUSTOMER_NAMES = List.of(
            "Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Hannah"
    );

    private static final List<String> PRODUCT_NAMES = List.of(
            "Laptop", "Phone", "Tablet", "Headphones", "Monitor", "Keyboard", "Mouse", "Speaker"
    );

    private static final List<OrderStatus> STATUSES = List.of(
            OrderStatus.PENDING, OrderStatus.PROCESSING, OrderStatus.SHIPPED, OrderStatus.CANCELLED
    );


    @Override
    public void run(String... args) throws Exception {
        if (orderRepository.count() > 0){
            return;
        }

        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < 50; i++){
            Order order = new Order();
            order.setCustomerName(randomElement(CUSTOMER_NAMES));
            order.setStatus(randomElement(STATUSES));
            order.setPaid(random.nextBoolean());
            order.setCreatedDate(LocalDateTime.now().minusDays(random.nextInt(30)));

            int itemCount = 1 + random.nextInt(4);
            List<OrderItem> items = new ArrayList<>();
            double total = 0;

            for (int j = 0; j < itemCount; j++){
                OrderItem item = new OrderItem();
                item.setProductName(randomElement(PRODUCT_NAMES));
                item.setQuantity(1 + random.nextInt(3));
                item.setPrice(10 + (100 * random.nextDouble()));
                item.setOrder(order);
                items.add(item);

                total += item.getQuantity() * item.getPrice();
            }

            order.setTotal(Math.round(total * 100.0) / 100.0);
            order.setOrderItemList(items);
            orders.add(order);
        }
        orderRepository.saveAll(orders);
        System.out.println("Seeded 50 demo orders.");
    }

    private <T> T randomElement(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
