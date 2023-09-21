package ru.aston.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDtoWithOrders {

    private Long id;

    private String name;

    private List<OrderDto> orders = new ArrayList<>();

    public UserDtoWithOrders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }
}
