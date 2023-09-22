package ru.aston.controller;


import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.aston.TestContextLoader;
import ru.aston.dto.NewUserDto;
import ru.aston.dto.UpdateUserDto;
import ru.aston.dto.UserDto;
import ru.aston.dto.UserDtoWithOrders;
import ru.aston.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTest extends TestContextLoader {


    @Test
    public void shouldCreateUser() throws Exception {
        //given
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setName("test_name");

        //when
        var response = mockMvc.perform(
                        post("/user")
                                .content(objectMapper.writeValueAsString(newUserDto))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        var result = objectMapper.readValue(response, UserDto.class);

        //then
        assertNotNull(result.getId());
        assertEquals(result.getName(), newUserDto.getName());
    }

    @Test
    public void shouldGetUser() throws Exception {
        //given
        User user = new User();
        user.setName("test_user");
        user = userRepository.save(user);

        //when
        var response = mockMvc.perform(
                        get("/user/" + user.getId())
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful()).andReturn().getResponse().getContentAsString();

        //then
        var result = objectMapper.readValue(response, UserDtoWithOrders.class);
        assertNotNull(result.getId());
        assertEquals(result.getName(), user.getName());
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //given
        User user = new User();
        user.setName("test_user");
        user = userRepository.save(user);
        var updateUserDto = new UpdateUserDto();
        updateUserDto.setName("new_name");

        //when
        var response = mockMvc.perform(
                        patch("/user/" + user.getId())
                                .content(objectMapper.writeValueAsString(updateUserDto))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful()).andReturn().getResponse().getContentAsString();

        //then
        var result = objectMapper.readValue(response, UserDtoWithOrders.class);
        assertNotNull(result.getId());
        assertEquals(result.getName(), "new_name");
    }


    @Test
    public void shouldDeleteUser() throws Exception {
        //given
        User user = new User();
        user.setName("test_user");
        user = userRepository.save(user);

        //when
        mockMvc.perform(
                        delete("/user/" + user.getId())
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful());

        //then
        assertFalse(userRepository.findById(user.getId()).isPresent());
    }

}
