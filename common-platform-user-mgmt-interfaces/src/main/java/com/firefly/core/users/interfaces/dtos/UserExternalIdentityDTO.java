package com.firefly.core.users.interfaces.dtos;

import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO representing an external identity linked to a user account.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserExternalIdentityDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @FilterableId
    private UUID userAccountId;

    private String provider;

    @FilterableId
    private String subjectId;

    private String email;
    private Boolean isPrimary;
    private OffsetDateTime linkedAt;
    private OffsetDateTime createdAt;
    private UUID createdBy;
    private OffsetDateTime updatedAt;
    private UUID updatedBy;
}