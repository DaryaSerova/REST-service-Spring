package ru.aston.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.dto.NewOrderDto;
import ru.aston.dto.OrderDto;
import ru.aston.dto.UpdateOrderDto;
import ru.aston.jpa.OrderPersistService;
import ru.aston.jpa.UserPersistService;
import ru.aston.mapper.OrderMapper;
import ru.aston.model.Order;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderPersistService orderPersistService;

    @Autowired
    UserPersistService userPersistService;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDto createOrder(NewOrderDto newOrderDto, Long userId) {
        var user = userPersistService.findUserById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException(String.format("User with id = %s was not found.", userId));
        }
        Order order = orderPersistService.createOrder(orderMapper.toOrder(newOrderDto, userId));
        return orderMapper.toOrderDto(order);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Optional<Order> orderOpt = orderPersistService.getOrderById(orderId);
        if (orderOpt.isEmpty()) {
            throw new RuntimeException(String.format("Order with id = %s was not found.", orderId));
        }
        return orderMapper.toOrderDto(orderOpt.get());
    }

    @Override
    public void updateOrder(UpdateOrderDto updateOrderDto, Long orderId, Long userId) {

        Optional<Order> orderOpt = orderPersistService.getOrderById(orderId);

        if (orderOpt.isEmpty()) {
            throw new RuntimeException(String.format("Order with id = %s was not found.", orderId));
        } else if (orderOpt.get().getUserId() != userId) {
            throw new RuntimeException(
                    String.format("Order with id = %s does not belong to user with id = %s.", orderId, userId));
        }

        Order order = orderOpt.get();
        orderMapper.mergeToOrder(updateOrderDto, order);
        orderPersistService.updateOrder(order);
    }

    @Override
    public void deleteOrderById(Long orderId, Long userId) {

        Optional<Order> orderOpt = orderPersistService.getOrderById(orderId);

        if (orderOpt.isEmpty()) {
            throw new RuntimeException(String.format("Order with id = %s was not found.", orderId));
        } else if (orderOpt.get().getUserId() != userId) {
            throw new RuntimeException(
                    String.format("Order with id = %s does not belong to user with id = %s.", orderId, userId));
        }

        orderPersistService.deleteOrder(orderId);

    }
}
