package com.catalis.core.users.web.controllers;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.users.core.services.RoleService;
import com.catalis.core.users.interfaces.dtos.RoleDTO;
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
@RequestMapping("/api/v1/roles")
@Tag(name = "Roles", description = "API for managing roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Operation(summary = "Get all roles with filtering", description = "Returns a paginated list of roles based on filter criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved roles",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class)))
    })
    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PaginationResponse<RoleDTO>> filterRoles(@RequestBody FilterRequest<RoleDTO> filterRequest) {
        return roleService.filterRoles(filterRequest);
    }

    @Operation(summary = "Get role by ID", description = "Returns a role by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved role",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @GetMapping(value = "/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<RoleDTO> getRoleById(
            @Parameter(description = "ID of the role to retrieve", required = true)
            @PathVariable Long roleId) {
        return roleService.getRoleById(roleId);
    }

    @Operation(summary = "Create a new role", description = "Creates a new role and returns the created role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleDTO.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.createRole(roleDTO);
    }

    @Operation(summary = "Update an existing role", description = "Updates an existing role and returns the updated role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role successfully updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @PutMapping(value = "/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<RoleDTO> updateRole(
            @Parameter(description = "ID of the role to update", required = true)
            @PathVariable Long roleId,
            @RequestBody RoleDTO roleDTO) {
        return roleService.updateRole(roleId, roleDTO);
    }

    @Operation(summary = "Delete a role", description = "Deletes a role by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteRole(
            @Parameter(description = "ID of the role to delete", required = true)
            @PathVariable Long roleId) {
        return roleService.deleteRole(roleId);
    }
}