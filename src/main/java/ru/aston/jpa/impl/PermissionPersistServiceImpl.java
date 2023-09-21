package ru.aston.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.dto.PermissionDto;
import ru.aston.dto.UserPermissionDto;
import ru.aston.jpa.PermissionPersistService;
import ru.aston.mapper.PermissionMapper;
import ru.aston.model.Permission;
import ru.aston.repository.PermissionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionPersistServiceImpl implements PermissionPersistService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    @Transactional
    public PermissionDto addPermission(Long userId, Long permissionId) {

        PermissionRepository.PermissionProjection permission = permissionRepository.savePermission(userId, permissionId);

        return new PermissionDto(permission.getPermissionId(), permission.getUserId());
    }

    @Override
    public UserPermissionDto findPermissionByUserId(Long userId) {

        List<PermissionRepository.PermissionProjection> userPermissionProjection =
                permissionRepository.findPermissionByUserId(userId);

        if (userPermissionProjection == null) {
            throw new RuntimeException(String.format("User with id = %s have not permission.", userId));
        }
        List<Permission> permissions = userPermissionProjection.stream().map(permissionMapper::map)
                .collect(Collectors.toList());

        return new UserPermissionDto(userPermissionProjection.get(0).getUserId(),
                userPermissionProjection.get(0).getUserName(),
                permissions);

    }

    @Override
    @Transactional
    public void deletePermission(Long permissionId, Long userId) {
        permissionRepository.deletePermissionByPermissionIdAndUserId(permissionId, userId);
    }
}
