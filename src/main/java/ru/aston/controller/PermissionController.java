package ru.aston.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.NewPermissionUserDto;
import ru.aston.dto.PermissionDto;
import ru.aston.dto.UserPermissionDto;
import ru.aston.service.permission.PermissionService;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("{userId}")
    public UserPermissionDto getPermissionByUserId(@PathVariable("userId") Long userId) {
        return permissionService.getPermissionByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionDto addPermission(@RequestBody NewPermissionUserDto newPermissionUserDto) {

        return permissionService.addPermission(newPermissionUserDto);

    }

    @DeleteMapping("/{permissionId}/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePermission(@PathVariable("permissionId") Long permissionId,
                                 @PathVariable("userId") Long userId) {
        permissionService.deletePermission(permissionId, userId);

    }

}
