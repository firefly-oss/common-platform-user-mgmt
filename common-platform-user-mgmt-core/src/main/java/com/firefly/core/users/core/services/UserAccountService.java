package com.firefly.core.users.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.users.interfaces.dtos.UserAccountDTO;

import reactor.core.publisher.Mono;

/**
 * Service interface for managing user accounts.
 */
public interface UserAccountService {
    /**
     * Filters the user accounts based on the given criteria.
     *
     * @param filterRequest the request object containing filtering criteria for UserAccountDTO
     * @return a reactive {@code Mono} emitting a {@code PaginationResponse} containing the filtered list of user accounts
     */
    Mono<PaginationResponse<UserAccountDTO>> filterUserAccounts(FilterRequest<UserAccountDTO> filterRequest);
    
    /**
     * Creates a new user account based on the provided information.
     *
     * @param userAccountDTO the DTO object containing details of the user account to be created
     * @return a Mono that emits the created UserAccountDTO object
     */
    Mono<UserAccountDTO> createUserAccount(UserAccountDTO userAccountDTO);
    
    /**
     * Updates an existing user account with updated information.
     *
     * @param userAccountId the unique identifier of the user account to be updated
     * @param userAccountDTO the data transfer object containing the updated details of the user account
     * @return a reactive Mono containing the updated UserAccountDTO
     */
    Mono<UserAccountDTO> updateUserAccount(Long userAccountId, UserAccountDTO userAccountDTO);
    
    /**
     * Deletes a user account identified by its unique ID.
     *
     * @param userAccountId the unique identifier of the user account to be deleted
     * @return a Mono that completes when the user account is successfully deleted or errors if the deletion fails
     */
    Mono<Void> deleteUserAccount(Long userAccountId);
    
    /**
     * Retrieves a user account by its unique identifier.
     *
     * @param userAccountId the unique identifier of the user account to retrieve
     * @return a Mono emitting the {@link UserAccountDTO} representing the user account if found,
     *         or an empty Mono if the user account does not exist
     */
    Mono<UserAccountDTO> getUserAccountById(Long userAccountId);
}