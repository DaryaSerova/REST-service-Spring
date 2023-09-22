package ru.aston.repository;

import org.junit.jupiter.api.Test;
import ru.aston.TestContextLoader;
import ru.aston.model.Order;
import ru.aston.model.User;

import static org.junit.jupiter.api.Assertions.*;

public class OrderRepositoryTest extends TestContextLoader {


    @Test
    public void shouldCreateOrder() {
        //given
        User user = new User();
        user.setName("test_user");
        user = userRepository.save(user);

        Order order = new Order();
        order.setName("test_order");
        order.setUserId(user.getId());

        //when
        var result = orderRepository.save(order);

        //then
        assertNotNull(order.getId());
        assertTrue(order.getName().equals(result.getName()));
    }


    @Test
    public void shouldFindOrder() {
        //given
        User user = new User();
        user.setName("test_user");
        user = userRepository.save(user);

        Order order = new Order();
        order.setName("test_order");
        order.setUserId(user.getId());
        order = orderRepository.save(order);

        //when
        Order result = orderRepository.findById(order.getId()).get();

        //then
        assertTrue(order.getId().equals(result.getId()));
        assertTrue(order.getName().equals(result.getName()));
    }

    @Test
    public void shouldUpdateOrder() {
        //given
        User user = new User();
        user.setName("test_user");
        user = userRepository.save(user);

        Order order = new Order();
        order.setName("test_order");
        order.setUserId(user.getId());
        order = orderRepository.save(order);

        order.setName("update_name");

        //when
        Order result = orderRepository.save(order);

        //then
        assertTrue(order.getId().equals(result.getId()));
        assertTrue(order.getName().equals(result.getName()));
    }

    @Test
    public void shouldDeleteOrder() {
        //given
        User user = new User();
        user.setName("test_user");
        user = userRepository.save(user);

        Order order = new Order();
        order.setName("test_order");
        order.setUserId(1L);
        order.setUserId(user.getId());
        order = orderRepository.save(order);

        Long orderId = order.getId();

        //when
        orderRepository.deleteById(order.getId());

        //then
        assertFalse(
                orderRepository.findById(orderId).isPresent());
    }
}
