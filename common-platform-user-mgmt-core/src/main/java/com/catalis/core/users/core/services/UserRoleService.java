package com.catalis.core.users.core.services;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.users.interfaces.dtos.UserRoleDTO;

import reactor.core.publisher.Mono;

/**
 * Service interface for managing user roles.
 */
public interface UserRoleService {
    /**
     * Filters the user roles based on the given criteria.
     *
     * @param filterRequest the request object containing filtering criteria for UserRoleDTO
     * @return a reactive {@code Mono} emitting a {@code PaginationResponse} containing the filtered list of user roles
     */
    Mono<PaginationResponse<UserRoleDTO>> filterUserRoles(FilterRequest<UserRoleDTO> filterRequest);
    
    /**
     * Creates a new user role based on the provided information.
     *
     * @param userRoleDTO the DTO object containing details of the user role to be created
     * @return a Mono that emits the created UserRoleDTO object
     */
    Mono<UserRoleDTO> createUserRole(UserRoleDTO userRoleDTO);
    
    /**
     * Updates an existing user role with updated information.
     *
     * @param userRoleId the unique identifier of the user role to be updated
     * @param userRoleDTO the data transfer object containing the updated details of the user role
     * @return a reactive Mono containing the updated UserRoleDTO
     */
    Mono<UserRoleDTO> updateUserRole(Long userRoleId, UserRoleDTO userRoleDTO);
    
    /**
     * Deletes a user role identified by its unique ID.
     *
     * @param userRoleId the unique identifier of the user role to be deleted
     * @return a Mono that completes when the user role is successfully deleted or errors if the deletion fails
     */
    Mono<Void> deleteUserRole(Long userRoleId);
    
    /**
     * Retrieves a user role by its unique identifier.
     *
     * @param userRoleId the unique identifier of the user role to retrieve
     * @return a Mono emitting the {@link UserRoleDTO} representing the user role if found,
     *         or an empty Mono if the user role does not exist
     */
    Mono<UserRoleDTO> getUserRoleById(Long userRoleId);
}