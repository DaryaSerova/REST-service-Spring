package ru.aston.jpa;

import ru.aston.dto.PermissionDto;
import ru.aston.dto.UserPermissionDto;

public interface PermissionPersistService {

    UserPermissionDto findPermissionByUserId(Long userId);

    PermissionDto addPermission(Long userId, Long permissionId);

    void deletePermission(Long permissionId, Long userId);
}
