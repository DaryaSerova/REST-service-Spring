package ru.aston.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.aston.TestContextLoader;
import ru.aston.dto.NewOrderDto;
import ru.aston.dto.OrderDto;
import ru.aston.dto.UpdateOrderDto;
import ru.aston.model.Order;
import ru.aston.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class OrderControllerTest extends TestContextLoader {

    @Test
    public void shouldCreateOrder() throws Exception {
        //given
        NewOrderDto newOrderDto = new NewOrderDto();
        newOrderDto.setName("test_name");
        User user = createUser();


        //when
        var response = mockMvc.perform(
                        post("/order/" + user.getId())
                                .content(objectMapper.writeValueAsString(newOrderDto))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        var result = objectMapper.readValue(response, OrderDto.class);

        //then
        assertNotNull(result.getId());
        assertEquals(result.getName(), newOrderDto.getName());
    }

    @Test
    public void shouldGetOrder() throws Exception {
        //given
        User user = createUser();
        Order order = createOrder(user.getId());


        //when
        var response = mockMvc.perform(
                        get("/order/" + order.getId())
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful()).andReturn().getResponse().getContentAsString();
        var result = objectMapper.readValue(response, OrderDto.class);

        //then
        assertNotNull(result.getId());
        assertEquals(result.getName(), order.getName());
        assertEquals(result.getUserId(), order.getUserId());
    }

    @Test
    public void shouldUpdateOrder() throws Exception {
        //given
        User user = createUser();
        Order order = createOrder(user.getId());
        var updateOrderDto = new UpdateOrderDto();
        updateOrderDto.setName("new_name");

        //when
        var response = mockMvc.perform(
                        patch("/order/" + user.getId() + "/" + order.getId())
                                .content(objectMapper.writeValueAsString(updateOrderDto))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful()).andReturn().getResponse().getContentAsString();
        var result = objectMapper.readValue(response, OrderDto.class);

        //then
        assertNotNull(result.getId());
        assertEquals(result.getName(), "new_name");
    }

    @Test
    public void shouldDeleteOrder() throws Exception {
        //given
        User user = createUser();
        Order order = createOrder(user.getId());

        //when
        mockMvc.perform(
                        delete("/order/" + user.getId() + "/" + order.getId())
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful()).andReturn();


        //then
        assertFalse(orderRepository.findById(order.getId()).isPresent());
    }

    private User createUser() {
        User user = new User();
        user.setName("test_user");
        return userRepository.save(user);
    }

    private Order createOrder(Long userId) {
        Order order = new Order();
        order.setName("test_name");
        order.setUserId(userId);
        return orderRepository.save(order);
    }
}
