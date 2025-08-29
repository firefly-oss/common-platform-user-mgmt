package com.firefly.core.users.models.repositories;

import com.firefly.core.users.models.entities.UserExternalIdentity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository interface for UserExternalIdentity entity.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface UserExternalIdentityRepository extends BaseRepository<UserExternalIdentity, Long> {
    
    /**
     * Find external identities by user account ID.
     *
     * @param userAccountId the user account ID
     * @return a Flux of UserExternalIdentity entities
     */
    Flux<UserExternalIdentity> findByUserAccountId(Long userAccountId);
    
    /**
     * Find external identities by provider.
     *
     * @param provider the provider
     * @return a Flux of UserExternalIdentity entities
     */
    Flux<UserExternalIdentity> findByProvider(String provider);
    
    /**
     * Find an external identity by provider and subject ID.
     *
     * @param provider the provider
     * @param subjectId the subject ID
     * @return a Mono of UserExternalIdentity entity
     */
    Mono<UserExternalIdentity> findByProviderAndSubjectId(String provider, String subjectId);
    
    /**
     * Find an external identity by user account ID and is primary flag.
     *
     * @param userAccountId the user account ID
     * @param isPrimary the is primary flag
     * @return a Mono of UserExternalIdentity entity
     */
    Mono<UserExternalIdentity> findByUserAccountIdAndIsPrimary(Long userAccountId, Boolean isPrimary);
    
    /**
     * Delete external identities by user account ID.
     *
     * @param userAccountId the user account ID
     * @return a Mono of Void
     */
    Mono<Void> deleteByUserAccountId(Long userAccountId);
}