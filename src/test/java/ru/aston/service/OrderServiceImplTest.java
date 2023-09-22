package ru.aston.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.aston.TestContextLoader;
import ru.aston.dto.NewOrderDto;
import ru.aston.dto.OrderDto;
import ru.aston.dto.UpdateOrderDto;
import ru.aston.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static ru.aston.util.Fixture.generateUpdateOrderDto;

public class OrderServiceImplTest extends TestContextLoader {


    @Test
    public void shouldCreateOrder() {

        //given
        NewOrderDto newOrderDto = new NewOrderDto();
        newOrderDto.setName("Test_name");
        var user = new User();
        user.setName("test");
        user = userRepository.save(user);
        //when
        OrderDto result = orderService.createOrder(newOrderDto, user.getId());

        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    public void shouldThrowExceptionCreateOrder() {

        //given
        //when
        //then
        assertThrows(
                RuntimeException.class,
                () -> orderService.createOrder(null, null));
    }

    @Test
    public void shouldUpdateOrder() {

        //given
        UpdateOrderDto updateOrderDto = generateUpdateOrderDto();
        NewOrderDto newOrderDto = new NewOrderDto();
        newOrderDto.setName("Test_name");
        var user = new User();
        user.setName("test");
        user = userRepository.save(user);
        var order = orderService.createOrder(newOrderDto, user.getId());

        //when
        orderService.updateOrder(updateOrderDto, order.getId(), user.getId());
        var result = orderService.getOrderById(order.getId());

        //then
        assertTrue(order.getId().equals(result.getId()));
        assertFalse(order.getName().equals(result.getName()));

    }

    @Test
    public void shouldDeleteOrder() {

        //given
        var user = new User();
        user.setName("test");
        user = userRepository.save(user);
        NewOrderDto newOrderDto = new NewOrderDto();
        newOrderDto.setName("Test_name");
        var order = orderService.createOrder(newOrderDto, user.getId());

        //when
        orderService.deleteOrderById(order.getId(), order.getUserId());

        //then
        assertThrows(
                RuntimeException.class,
                () -> orderService.getOrderById(order.getId()));
    }


    @Test
    public void shouldFindOrder() {

        //given
        var user = new User();
        user.setName("test");
        user = userRepository.save(user);
        NewOrderDto newOrderDto = new NewOrderDto();
        newOrderDto.setName("Test_name");
        var order = orderService.createOrder(newOrderDto, user.getId());

        //when
        OrderDto result = orderService.getOrderById(order.getId());

        //then
        assertTrue(order.getId().equals(result.getId()));
        assertTrue(order.getName().equals(result.getName()));
    }


}
