package com.catalis.core.users.web.controllers;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.users.core.services.PermissionService;
import com.catalis.core.users.interfaces.dtos.PermissionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/permissions")
@Tag(name = "Permissions", description = "API for managing permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Operation(summary = "Get all permissions with filtering", description = "Returns a paginated list of permissions based on filter criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved permissions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class)))
    })
    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PaginationResponse<PermissionDTO>> filterPermissions(@RequestBody FilterRequest<PermissionDTO> filterRequest) {
        return permissionService.filterPermissions(filterRequest);
    }

    @Operation(summary = "Get permission by ID", description = "Returns a permission by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved permission",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PermissionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Permission not found")
    })
    @GetMapping(value = "/{permissionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PermissionDTO> getPermissionById(
            @Parameter(description = "ID of the permission to retrieve", required = true)
            @PathVariable Long permissionId) {
        return permissionService.getPermissionById(permissionId);
    }

    @Operation(summary = "Create a new permission", description = "Creates a new permission and returns the created permission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permission successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PermissionDTO.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PermissionDTO> createPermission(@RequestBody PermissionDTO permissionDTO) {
        return permissionService.createPermission(permissionDTO);
    }

    @Operation(summary = "Update an existing permission", description = "Updates an existing permission and returns the updated permission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission successfully updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PermissionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Permission not found")
    })
    @PutMapping(value = "/{permissionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PermissionDTO> updatePermission(
            @Parameter(description = "ID of the permission to update", required = true)
            @PathVariable Long permissionId,
            @RequestBody PermissionDTO permissionDTO) {
        return permissionService.updatePermission(permissionId, permissionDTO);
    }

    @Operation(summary = "Delete a permission", description = "Deletes a permission by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Permission successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Permission not found")
    })
    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePermission(
            @Parameter(description = "ID of the permission to delete", required = true)
            @PathVariable Long permissionId) {
        return permissionService.deletePermission(permissionId);
    }
}