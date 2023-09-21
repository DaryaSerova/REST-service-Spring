package ru.aston.dto;

public class PermissionDto {

    private Long permissionId;

    private Long userId;

    public PermissionDto(Long permissionId, Long userId) {
        this.permissionId = permissionId;
        this.userId = userId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
