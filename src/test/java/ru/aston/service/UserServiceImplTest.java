package ru.aston.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.aston.TestContextLoader;
import ru.aston.dto.NewUserDto;
import ru.aston.dto.UpdateUserDto;
import ru.aston.dto.UserDto;

import static org.junit.jupiter.api.Assertions.*;
import static ru.aston.util.Fixture.generateUpdateUserDto;

public class UserServiceImplTest extends TestContextLoader {

    @Test
    public void shouldCreateUser() {

        //given
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setName("Test_name");

        //when
        UserDto result = userService.createUser(newUserDto);

        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    public void shouldThrowExceptionCreateUser() {

        //given
        //when
        //then
        assertThrows(
                RuntimeException.class,
                () -> userService.createUser(null));
    }

    @Test
    public void shouldUpdateUser() {

        //given
        UpdateUserDto updateUser = generateUpdateUserDto();
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setName("Test_name");
        var user = userService.createUser(newUserDto);


        //when
        userService.updateUser(updateUser, user.getId());
        var result = userService.getUserById(user.getId());

        //then
        assertTrue(user.getId().equals(result.getId()));
        assertFalse(user.getName().equals(result.getName()));

    }

    @Test
    public void shouldDeleteUser() throws Exception {

        //given
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setName("Test_name");
        var user = userService.createUser(newUserDto);

        //when
        userService.deleteUserById(user.getId());

        //then
        assertThrows(
                RuntimeException.class,
                () -> userService.getUserById(user.getId()));
    }

    @Test
    public void shouldFindUser() throws Exception {

        //given
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setName("Test_name");
        var user = userService.createUser(newUserDto);

        //when
        var result = userService.getUserById(user.getId());

        //then
        assertTrue(user.getId().equals(result.getId()));
        assertTrue(user.getName().equals(result.getName()));
    }


}
