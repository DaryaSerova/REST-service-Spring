package ru.aston.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.aston.TestContextLoader;
import ru.aston.dto.NewPermissionUserDto;
import ru.aston.dto.NewUserDto;
import ru.aston.dto.PermissionDto;
import ru.aston.model.Permission;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.aston.model.PermissionType.ADMIN;

@Transactional
public class PermissionServiceImplTest extends TestContextLoader {


    @Test
    public void shouldAddPermission() {

        //given
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setName("Test_name");
        var user = userService.createUser(newUserDto);
        var permission = new Permission(ADMIN);
        permissionRepository.save(permission);

        //when
        PermissionDto result = permissionService.addPermission(new NewPermissionUserDto(permission.getId(), user.getId()));

        //then
        Assertions.assertNotNull(result.getPermissionId());
        Assertions.assertNotNull(result.getUserId());
    }

    @Test
    public void shouldFindPermission() {

        //given
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setName("Test_name");
        var user = userService.createUser(newUserDto);
        var permission = new Permission(ADMIN);
        permissionRepository.save(permission);
        var permissionDto = permissionService.addPermission(new NewPermissionUserDto(permission.getId(), user.getId()));

        //when
        var result = permissionService.getPermissionByUserId(user.getId());

        //then
        assertTrue(result.getPermission().get(0).getId().equals(permissionDto.getPermissionId()));
        assertTrue(result.getUserId().equals(permissionDto.getUserId()));
    }


    @Test
    public void shouldDeletePermission() {

        //given
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setName("Test_name");
        var user = userService.createUser(newUserDto);
        var permission = new Permission(ADMIN);
        permissionRepository.save(permission);
        var permissionDto = permissionService.addPermission(new NewPermissionUserDto(permission.getId(), user.getId()));

        //when
        permissionService.deletePermission(permissionDto.getPermissionId(), user.getId());

        //then
        assertThrows(
                RuntimeException.class,
                () -> permissionService.getPermissionByUserId(user.getId()));
    }

}
