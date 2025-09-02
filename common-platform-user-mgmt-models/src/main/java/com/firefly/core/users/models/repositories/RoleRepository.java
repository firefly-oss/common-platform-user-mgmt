package com.firefly.core.users.models.repositories;

import com.firefly.core.users.interfaces.enums.ScopeTypeEnum;
import com.firefly.core.users.models.entities.Role;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Repository interface for Role entity.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface RoleRepository extends BaseRepository<Role, UUID> {
    
    /**
     * Find a role by its name.
     *
     * @param name the role name
     * @return a Mono of Role entity
     */
    Mono<Role> findByName(String name);
    
    /**
     * Find roles by assignable status.
     *
     * @param isAssignable the assignable status
     * @return a Flux of Role entities
     */
    Flux<Role> findByIsAssignable(Boolean isAssignable);
    
    /**
     * Find roles by scope type.
     *
     * @param scopeType the scope type
     * @return a Flux of Role entities
     */
    Flux<Role> findByScopeType(ScopeTypeEnum scopeType);
    
    /**
     * Check if a role with the given name exists.
     *
     * @param name the role name
     * @return a Mono of Boolean
     */
    Mono<Boolean> existsByName(String name);
}