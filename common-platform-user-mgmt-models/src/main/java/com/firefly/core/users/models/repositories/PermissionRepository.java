package com.firefly.core.users.models.repositories;

import com.firefly.core.users.models.entities.Permission;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository interface for Permission entity.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface PermissionRepository extends BaseRepository<Permission, Long> {
    
    /**
     * Find a permission by its name.
     *
     * @param name the permission name
     * @return a Mono of Permission entity
     */
    Mono<Permission> findByName(String name);
    
    /**
     * Find permissions by domain.
     *
     * @param domain the domain
     * @return a Flux of Permission entities
     */
    Flux<Permission> findByDomain(String domain);
    
    /**
     * Check if a permission with the given name exists.
     *
     * @param name the permission name
     * @return a Mono of Boolean
     */
    Mono<Boolean> existsByName(String name);
}