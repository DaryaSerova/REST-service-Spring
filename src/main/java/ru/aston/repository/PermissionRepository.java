package ru.aston.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aston.model.Permission;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Modifying
    @Query(value = "INSERT INTO user_permission_t(permission_id, user_id) VALUES(:permissionId, :userId);",
            nativeQuery = true)
    PermissionProjection savePermission(@Param("userId") Long userId, @Param("permissionId") Long permissionId);


    @Query(value = "SELECT usperm.user_id AS userId, u.name AS userName, perm.type AS permissionType, " +
            "perm.id AS permissionId " +
            "FROM user_permission_t AS usperm " +
            "LEFT JOIN user_t AS u ON usperm.user_id = u.id " +
            "LEFT JOIN permission_t AS perm ON usperm.permission_id = perm.id " +
            "WHERE usperm.user_id = :userId ", nativeQuery = true)
    List<PermissionProjection> findPermissionByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "DELETE FROM user_permission_t WHERE permission_id = :permissionId AND user_id = :userId;",
            nativeQuery = true)
    void deletePermissionByPermissionIdAndUserId(Long permissionId, Long userId);

    interface PermissionProjection {
        Long getUserId();

        String getUserName();

        String getPermissionType();

        Long getPermissionId();
    }
}
