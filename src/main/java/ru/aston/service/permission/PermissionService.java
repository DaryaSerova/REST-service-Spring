package ru.aston.service.permission;

import ru.aston.dto.NewPermissionUserDto;
import ru.aston.dto.PermissionDto;
import ru.aston.dto.UserPermissionDto;

public interface PermissionService {

    UserPermissionDto getPermissionByUserId(Long userId);

    PermissionDto addPermission(NewPermissionUserDto newPermissionUserDto);

    void deletePermission(Long permissionId, Long userId);
}
