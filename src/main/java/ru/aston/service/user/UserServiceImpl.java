package ru.aston.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.dto.*;
import ru.aston.jpa.UserPersistService;
import ru.aston.mapper.OrderMapper;
import ru.aston.mapper.UserMapper;
import ru.aston.model.Order;
import ru.aston.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserPersistService userPersistService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public UserDto createUser(NewUserDto newUserDto) {

        if (newUserDto.getName() == null) {
            throw new RuntimeException("An empty value cannot be passed.");
        }

        return userMapper.toUserDto(userPersistService.createUser(userMapper.toUser(newUserDto)));
    }

    @Override
    public UserDtoWithOrders getUserById(Long userId) {

        User user = findUserById(userId);

        List<OrderDto> orders = new ArrayList<>();
        for (Order order : user.getOrder()) {
            OrderDto orderDto = orderMapper.toOrderDto(order);
            orders.add(orderDto);
        }

        UserDtoWithOrders userDto = userMapper.toUserDtoWithOrders(user);
        userDto.setOrders(orders);

        return userDto;
    }

    @Override
    public UserDtoWithOrders updateUser(UpdateUserDto updateUserDto, Long userId) {

        User user = findUserById(userId);
        userMapper.mergeToUser(updateUserDto, user);
        userPersistService.updateUser(user);
        return getUserById(userId);
    }

    @Override
    public void deleteUserById(Long userId) {

        findUserById(userId);
        userPersistService.deleteUser(userId);
    }

    private User findUserById(Long userId) {
        Optional<User> userOpt = userPersistService.findUserById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException(String.format("User with id = %s was not found.", userId));
        }

        return userOpt.get();
    }
}
