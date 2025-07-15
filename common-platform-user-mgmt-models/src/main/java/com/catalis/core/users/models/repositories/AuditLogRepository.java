package com.catalis.core.users.models.repositories;

import com.catalis.core.users.models.entities.AuditLog;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Repository interface for AuditLog entity.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface AuditLogRepository extends BaseRepository<AuditLog, Long> {
    
    /**
     * Find audit logs by user account ID.
     *
     * @param userAccountId the user account ID
     * @return a Flux of AuditLog entities
     */
    Flux<AuditLog> findByUserAccountId(Long userAccountId);
    
    /**
     * Find audit logs by action.
     *
     * @param action the action performed
     * @return a Flux of AuditLog entities
     */
    Flux<AuditLog> findByAction(String action);
    
    /**
     * Find audit logs by resource.
     *
     * @param resource the resource affected
     * @return a Flux of AuditLog entities
     */
    Flux<AuditLog> findByResource(String resource);
}