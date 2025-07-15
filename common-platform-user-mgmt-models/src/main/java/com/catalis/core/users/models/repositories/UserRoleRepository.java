package com.catalis.core.users.models.repositories;

import com.catalis.core.users.models.entities.UserRole;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository interface for UserRole entity.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface UserRoleRepository extends BaseRepository<UserRole, Long> {
    
    /**
     * Find user roles by user account ID.
     *
     * @param userAccountId the user account ID
     * @return a Flux of UserRole entities
     */
    Flux<UserRole> findByUserAccountId(Long userAccountId);
    
    /**
     * Find user roles by role ID.
     *
     * @param roleId the role ID
     * @return a Flux of UserRole entities
     */
    Flux<UserRole> findByRoleId(Long roleId);
    
    /**
     * Find user roles by branch ID.
     *
     * @param branchId the branch ID
     * @return a Flux of UserRole entities
     */
    Flux<UserRole> findByBranchId(Long branchId);
    
    /**
     * Find user roles by distributor ID.
     *
     * @param distributorId the distributor ID
     * @return a Flux of UserRole entities
     */
    Flux<UserRole> findByDistributorId(Long distributorId);
    
    /**
     * Find a user role by user account ID, role ID, branch ID, and distributor ID.
     *
     * @param userAccountId the user account ID
     * @param roleId the role ID
     * @param branchId the branch ID
     * @param distributorId the distributor ID
     * @return a Mono of UserRole entity
     */
    Mono<UserRole> findByUserAccountIdAndRoleIdAndBranchIdAndDistributorId(
            Long userAccountId, Long roleId, Long branchId, Long distributorId);
    
    /**
     * Delete user roles by user account ID.
     *
     * @param userAccountId the user account ID
     * @return a Mono of Void
     */
    Mono<Void> deleteByUserAccountId(Long userAccountId);
    
    /**
     * Delete user roles by role ID.
     *
     * @param roleId the role ID
     * @return a Mono of Void
     */
    Mono<Void> deleteByRoleId(Long roleId);
}