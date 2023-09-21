package ru.aston.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.aston.dto.NewUserDto;
import ru.aston.dto.UpdateUserDto;
import ru.aston.dto.UserDto;
import ru.aston.dto.UserDtoWithOrders;
import ru.aston.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(NewUserDto newUserDto);

    UserDto toUserDto(User user);

    UserDtoWithOrders toUserDtoWithOrders(User user);

    void mergeToUser(UpdateUserDto updateUserDto, @MappingTarget User user);
}
