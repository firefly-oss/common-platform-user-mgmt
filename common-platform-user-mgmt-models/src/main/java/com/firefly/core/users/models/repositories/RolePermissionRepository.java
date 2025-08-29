package com.firefly.core.users.models.repositories;

import com.firefly.core.users.models.entities.RolePermission;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository interface for RolePermission entity.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface RolePermissionRepository extends BaseRepository<RolePermission, Long> {
    
    /**
     * Find role permissions by role ID.
     *
     * @param roleId the role ID
     * @return a Flux of RolePermission entities
     */
    Flux<RolePermission> findByRoleId(Long roleId);
    
    /**
     * Find role permissions by permission ID.
     *
     * @param permissionId the permission ID
     * @return a Flux of RolePermission entities
     */
    Flux<RolePermission> findByPermissionId(Long permissionId);
    
    /**
     * Find a role permission by role ID and permission ID.
     *
     * @param roleId the role ID
     * @param permissionId the permission ID
     * @return a Mono of RolePermission entity
     */
    Mono<RolePermission> findByRoleIdAndPermissionId(Long roleId, Long permissionId);
    
    /**
     * Delete role permissions by role ID.
     *
     * @param roleId the role ID
     * @return a Mono of Void
     */
    Mono<Void> deleteByRoleId(Long roleId);
    
    /**
     * Delete a role permission by role ID and permission ID.
     *
     * @param roleId the role ID
     * @param permissionId the permission ID
     * @return a Mono of Void
     */
    Mono<Void> deleteByRoleIdAndPermissionId(Long roleId, Long permissionId);
}