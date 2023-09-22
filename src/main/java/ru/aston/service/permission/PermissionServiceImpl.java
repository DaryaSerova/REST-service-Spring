package ru.aston.service.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.dto.NewPermissionUserDto;
import ru.aston.dto.PermissionDto;
import ru.aston.dto.UserPermissionDto;
import ru.aston.jpa.PermissionPersistService;
import ru.aston.jpa.UserPersistService;
import ru.aston.model.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionPersistService permissionPersistService;

    @Autowired
    UserPersistService userPersistService;

    public UserPermissionDto getPermissionByUserId(Long userId) {
        Optional<User> userOpt = userPersistService.findUserById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException(String.format("User with id = %s was not found.", userId));
        }
        return permissionPersistService.findPermissionByUserId(userId);
    }

    @Override
    public PermissionDto addPermission(NewPermissionUserDto newPermissionUserDto) {
        Optional<User> userOpt = userPersistService.findUserById(newPermissionUserDto.getUserId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException(String.format("User with id = %s was not found.",
                    newPermissionUserDto.getUserId()));
        }

        Long userId = newPermissionUserDto.getUserId();
        Long permissionId = newPermissionUserDto.getPermissionId();

        return permissionPersistService.addPermission(userId, permissionId);
    }

    @Override
    @Transactional
    public void deletePermission(Long permissionId, Long userId) {
        permissionPersistService.deletePermission(permissionId, userId);
    }
}
