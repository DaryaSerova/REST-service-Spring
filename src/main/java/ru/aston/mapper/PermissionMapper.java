package ru.aston.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.model.Permission;
import ru.aston.repository.PermissionRepository;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    @Mapping(target = "id", source = "permissionId")
    @Mapping(target = "type", source = "permissionType")
    Permission map(PermissionRepository.PermissionProjection projection);

}
