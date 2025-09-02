package com.firefly.core.users.interfaces.dtos;

import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO representing an audit log entry in the system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @FilterableId
    @NotNull(message = "User account ID is required")
    private UUID userAccountId;

    @NotBlank(message = "Action is required")
    @Size(min = 1, max = 100, message = "Action must be between 1 and 100 characters")
    private String action;

    @NotBlank(message = "Resource is required")
    @Size(min = 1, max = 100, message = "Resource must be between 1 and 100 characters")
    private String resource;

    @FilterableId
    @NotBlank(message = "Resource ID is required")
    @Size(min = 1, max = 255, message = "Resource ID must be between 1 and 255 characters")
    private String resourceId;

    private JsonNode metadata;

    @Size(max = 45, message = "IP address must not exceed 45 characters")
    @Pattern(regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$|^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$",
             message = "IP address must be a valid IPv4 or IPv6 address")
    private String ipAddress;

    @NotNull(message = "Timestamp is required")
    private OffsetDateTime timestamp;
}