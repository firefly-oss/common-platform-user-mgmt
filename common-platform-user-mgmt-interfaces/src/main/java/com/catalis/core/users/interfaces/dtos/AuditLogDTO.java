package com.catalis.core.users.interfaces.dtos;

import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * DTO representing an audit log entry in the system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @FilterableId
    private Long userAccountId;

    private String action;
    private String resource;

    @FilterableId
    private String resourceId;

    private JsonNode metadata;
    private String ipAddress;
    private OffsetDateTime timestamp;
}