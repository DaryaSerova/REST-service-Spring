package ru.aston.dto;

public class NewPermissionUserDto {

    Long permissionId;

    Long userId;

    public Long getPermissionId() {
        return permissionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
