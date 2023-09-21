package ru.aston.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.aston.dto.NewOrderDto;
import ru.aston.dto.OrderDto;
import ru.aston.dto.UpdateOrderDto;
import ru.aston.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDto(Order order);

    Order toOrder(NewOrderDto newOrderDto, Long userId);

    void mergeToOrder(UpdateOrderDto updateOrderDto, @MappingTarget Order order);
}
