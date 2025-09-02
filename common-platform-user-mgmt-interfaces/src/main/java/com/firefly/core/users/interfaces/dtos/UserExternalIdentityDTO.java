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
    @NotNull(message = "User account ID is required")
    private UUID userAccountId;

    @NotBlank(message = "Provider is required")
    @Size(min = 1, max = 100, message = "Provider must be between 1 and 100 characters")
    private String provider;

    @FilterableId
    @NotBlank(message = "Subject ID is required")
    @Size(min = 1, max = 255, message = "Subject ID must be between 1 and 255 characters")
    private String subjectId;

    @Email(message = "Email must be a valid email address")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;

    @NotNull(message = "Primary status is required")
    private Boolean isPrimary;

    private OffsetDateTime linkedAt;
    private OffsetDateTime createdAt;
    private UUID createdBy;
    private OffsetDateTime updatedAt;
    private UUID updatedBy;
}