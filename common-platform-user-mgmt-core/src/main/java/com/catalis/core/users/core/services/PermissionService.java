package com.catalis.core.users.core.services;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.users.interfaces.dtos.PermissionDTO;

import reactor.core.publisher.Mono;

/**
 * Service interface for managing permissions.
 */
public interface PermissionService {
    /**
     * Filters the permissions based on the given criteria.
     *
     * @param filterRequest the request object containing filtering criteria for PermissionDTO
     * @return a reactive {@code Mono} emitting a {@code PaginationResponse} containing the filtered list of permissions
     */
    Mono<PaginationResponse<PermissionDTO>> filterPermissions(FilterRequest<PermissionDTO> filterRequest);
    
    /**
     * Creates a new permission based on the provided information.
     *
     * @param permissionDTO the DTO object containing details of the permission to be created
     * @return a Mono that emits the created PermissionDTO object
     */
    Mono<PermissionDTO> createPermission(PermissionDTO permissionDTO);
    
    /**
     * Updates an existing permission with updated information.
     *
     * @param permissionId the unique identifier of the permission to be updated
     * @param permissionDTO the data transfer object containing the updated details of the permission
     * @return a reactive Mono containing the updated PermissionDTO
     */
    Mono<PermissionDTO> updatePermission(Long permissionId, PermissionDTO permissionDTO);
    
    /**
     * Deletes a permission identified by its unique ID.
     *
     * @param permissionId the unique identifier of the permission to be deleted
     * @return a Mono that completes when the permission is successfully deleted or errors if the deletion fails
     */
    Mono<Void> deletePermission(Long permissionId);
    
    /**
     * Retrieves a permission by its unique identifier.
     *
     * @param permissionId the unique identifier of the permission to retrieve
     * @return a Mono emitting the {@link PermissionDTO} representing the permission if found,
     *         or an empty Mono if the permission does not exist
     */
    Mono<PermissionDTO> getPermissionById(Long permissionId);
}