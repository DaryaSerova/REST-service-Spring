package ru.aston.repository;

import org.junit.jupiter.api.Test;
import ru.aston.TestContextLoader;
import ru.aston.model.Permission;
import ru.aston.model.User;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.aston.model.PermissionType.ADMIN;

@Transactional
public class PermissionRepositoryTest extends TestContextLoader {


    @Test
    public void shouldCreatePermission() {
        //given
        User user = new User();
        user.setName("test_user");
        user = userRepository.save(user);
        var permission = new Permission(ADMIN);
        permissionRepository.save(permission);

        //when
        permissionRepository.savePermission(user.getId(), permission.getId());
        var result = permissionRepository.findPermissionByUserIdAndPermissionId(user.getId(), permission.getId());

        //then
        assertNotNull(result.getPermissionId());
        assertTrue(result.getUserId().equals(user.getId()));
    }


    @Test
    public void shouldFindOrder() {
        //given
        User user = new User();
        user.setName("test_user");
        user = userRepository.save(user);
        var permission = new Permission(ADMIN);
        permissionRepository.save(permission);
        permissionRepository.savePermission(user.getId(), permission.getId());

        //when
        var result = permissionRepository.findPermissionByUserId(user.getId());

        //then
        assertNotNull(result);
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getPermissionId() == permission.getId());
    }


    @Test
    public void shouldDeletePermission() {
        //given
        User user = new User();
        user.setName("test_user");
        user = userRepository.save(user);
        var permission = new Permission(ADMIN);
        permissionRepository.save(permission);
        permissionRepository.savePermission(user.getId(), permission.getId());
        var userId = user.getId();

        //when
        permissionRepository.deletePermissionByPermissionIdAndUserId(permission.getId(), user.getId());

        //then
        assertEquals(0, permissionRepository.findPermissionByUserId(userId).size());
    }
}
