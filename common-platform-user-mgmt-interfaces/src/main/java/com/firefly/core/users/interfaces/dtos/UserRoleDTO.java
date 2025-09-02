package com.firefly.core.users.interfaces.dtos;

import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO representing a user-role assignment in the system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @FilterableId
    @NotNull(message = "User account ID is required")
    private UUID userAccountId;

    @FilterableId
    @NotNull(message = "Role ID is required")
    private UUID roleId;

    @FilterableId
    private UUID branchId;

    @FilterableId
    private UUID distributorId;

    @NotNull(message = "Assigned at timestamp is required")
    private OffsetDateTime assignedAt;

    @NotNull(message = "Assigned by user ID is required")
    private UUID assignedBy;

    private OffsetDateTime createdAt;
    private UUID createdBy;
    private OffsetDateTime updatedAt;
    private UUID updatedBy;
}