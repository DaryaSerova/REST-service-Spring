package ru.aston.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.NewUserDto;
import ru.aston.dto.UpdateUserDto;
import ru.aston.dto.UserDto;
import ru.aston.dto.UserDtoWithOrders;
import ru.aston.service.user.UserService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody NewUserDto newUserDto) {
        return userService.createUser(newUserDto);
    }

    @GetMapping("/{userId}")
    public UserDtoWithOrders getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
    }

    @PatchMapping("/{userId}")
    public UserDtoWithOrders updateUser(@PathVariable("userId") Long userId,
                                        @RequestBody UpdateUserDto updateUserDto) {

        return userService.updateUser(updateUserDto, userId);
    }
}
