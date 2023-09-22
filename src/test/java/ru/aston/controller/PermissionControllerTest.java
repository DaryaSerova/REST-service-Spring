package ru.aston.controller;


import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.aston.TestContextLoader;
import ru.aston.dto.NewPermissionUserDto;
import ru.aston.dto.PermissionDto;
import ru.aston.dto.UserPermissionDto;
import ru.aston.model.Permission;
import ru.aston.model.User;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.aston.model.PermissionType.ADMIN;

@Transactional
public class PermissionControllerTest extends TestContextLoader {


    @Test
    public void shouldAddPermission() throws Exception {
        //given

        var user = createUser();
        var permission = new Permission(ADMIN);
        permissionRepository.save(permission);
        var newPermissionUserDto = new NewPermissionUserDto(permission.getId(), user.getId());

        //when
        var response = mockMvc.perform(
                        post("/permission")
                                .content(objectMapper.writeValueAsString(newPermissionUserDto))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        var result = objectMapper.readValue(response, PermissionDto.class);

        //then
        assertNotNull(result.getPermissionId());
        assertNotNull(result.getUserId());
        assertEquals(result.getUserId(), user.getId());
    }

    @Test
    public void shouldGetPermission() throws Exception {
        //given
        var user = createUser();
        var permission = new Permission(ADMIN);
        permissionRepository.save(permission);
        permissionRepository.savePermission(user.getId(), permission.getId());


        //when
        var response = mockMvc.perform(
                        get("/permission/" + user.getId())
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful()).andReturn().getResponse().getContentAsString();
        var result = objectMapper.readValue(response, UserPermissionDto.class);

        //then
        assertNotNull(result.getUserId());
        assertEquals(result.getPermission().size(), 1);
        assertEquals(result.getPermission().get(0).getId(), permission.getId());
    }


    @Test
    public void shouldDeletePermission() throws Exception {
        //given
        var user = createUser();
        var permission = new Permission(ADMIN);
        permissionRepository.save(permission);
        permissionRepository.savePermission(user.getId(), permission.getId());

        //when
        mockMvc.perform(
                        delete("/permission/" + permission.getId() + "/" + user.getId())
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful());

        //then
        assertNull(permissionRepository.findPermissionByUserIdAndPermissionId(user.getId(), permission.getId()));
    }


    private User createUser() {
        User user = new User();
        user.setName("test_user");
        return userRepository.save(user);
    }


}
