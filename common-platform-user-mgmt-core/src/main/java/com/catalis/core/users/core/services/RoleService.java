package com.catalis.core.users.core.services;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.users.interfaces.dtos.RoleDTO;

import reactor.core.publisher.Mono;

/**
 * Service interface for managing roles.
 */
public interface RoleService {
    /**
     * Filters the roles based on the given criteria.
     *
     * @param filterRequest the request object containing filtering criteria for RoleDTO
     * @return a reactive {@code Mono} emitting a {@code PaginationResponse} containing the filtered list of roles
     */
    Mono<PaginationResponse<RoleDTO>> filterRoles(FilterRequest<RoleDTO> filterRequest);
    
    /**
     * Creates a new role based on the provided information.
     *
     * @param roleDTO the DTO object containing details of the role to be created
     * @return a Mono that emits the created RoleDTO object
     */
    Mono<RoleDTO> createRole(RoleDTO roleDTO);
    
    /**
     * Updates an existing role with updated information.
     *
     * @param roleId the unique identifier of the role to be updated
     * @param roleDTO the data transfer object containing the updated details of the role
     * @return a reactive Mono containing the updated RoleDTO
     */
    Mono<RoleDTO> updateRole(Long roleId, RoleDTO roleDTO);
    
    /**
     * Deletes a role identified by its unique ID.
     *
     * @param roleId the unique identifier of the role to be deleted
     * @return a Mono that completes when the role is successfully deleted or errors if the deletion fails
     */
    Mono<Void> deleteRole(Long roleId);
    
    /**
     * Retrieves a role by its unique identifier.
     *
     * @param roleId the unique identifier of the role to retrieve
     * @return a Mono emitting the {@link RoleDTO} representing the role if found,
     *         or an empty Mono if the role does not exist
     */
    Mono<RoleDTO> getRoleById(Long roleId);
}