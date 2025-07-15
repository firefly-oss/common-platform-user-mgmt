package com.catalis.core.users.interfaces.dtos;

import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * DTO representing an external identity linked to a user account.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserExternalIdentityDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @FilterableId
    private Long userAccountId;

    private String provider;

    @FilterableId
    private String subjectId;

    private String email;
    private Boolean isPrimary;
    private OffsetDateTime linkedAt;
    private OffsetDateTime createdAt;
    private Long createdBy;
    private OffsetDateTime updatedAt;
    private Long updatedBy;
}