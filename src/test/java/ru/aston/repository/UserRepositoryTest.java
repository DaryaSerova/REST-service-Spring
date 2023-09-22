package ru.aston.repository;

import org.junit.jupiter.api.Test;
import ru.aston.TestContextLoader;
import ru.aston.model.User;

import static org.junit.jupiter.api.Assertions.*;


public class UserRepositoryTest extends TestContextLoader {

    @Test
    public void shouldCreateUser() {
        //given
        var user = new User();
        user.setName("test");

        //when
        var result = userRepository.save(user);

        //then
        assertNotNull(user.getId());
        assertTrue(user.getName().equals(result.getName()));
    }


    @Test
    public void shouldFindUser() {
        //given
        var user = new User();
        user.setName("test");
        user = userRepository.save(user);

        //when
        var result = userRepository.findById(user.getId()).get();

        //then
        assertTrue(user.getId().equals(result.getId()));
        assertTrue(user.getName().equals(result.getName()));
    }

    @Test
    public void shouldUpdateUser() {
        //given
        var user = new User();
        user.setName("test");
        user = userRepository.save(user);
        user.setName("test2");

        //when
        var result = userRepository.save(user);

        //then
        assertTrue(user.getId().equals(result.getId()));
        assertTrue(user.getName().equals(result.getName()));
    }

    @Test
    public void shouldDeleteUser() {
        //given
        var user = new User();
        user.setName("test");
        user = userRepository.save(user);
        var userId = user.getId();

        //when
        userRepository.deleteById(user.getId());

        //then
        assertFalse(userRepository.findById(userId).isPresent());
    }
}
