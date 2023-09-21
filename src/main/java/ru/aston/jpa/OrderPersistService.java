package ru.aston.jpa;

import ru.aston.model.Order;

import java.util.Optional;

public interface OrderPersistService {

    Optional<Order> getOrderById(Long orderId);

    Order createOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(Long orderId);
}
