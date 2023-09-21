package ru.aston.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.NewOrderDto;
import ru.aston.dto.OrderDto;
import ru.aston.dto.UpdateOrderDto;
import ru.aston.service.order.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable("orderId") Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody NewOrderDto newOrderDto,
                                @PathVariable("userId") Long userId) {
        return orderService.createOrder(newOrderDto, userId);
    }

    @PatchMapping("/{userId}/{orderId}")
    public void updateOrder(@PathVariable("userId") Long userId,
                            @PathVariable("orderId") Long orderId,
                            @RequestBody UpdateOrderDto updateOrderDto) {
        orderService.updateOrder(updateOrderDto, orderId, userId);
    }

    @DeleteMapping("/{userId}/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("userId") Long userId,
                            @PathVariable("orderId") Long orderId) {
        orderService.deleteOrderById(orderId, userId);
    }

}
