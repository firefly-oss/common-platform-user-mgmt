package com.firefly.core.users.interfaces.dtos;

import com.firefly.core.users.interfaces.enums.ThemePreferenceEnum;
import com.firefly.core.users.interfaces.enums.UserTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO representing a user account in the system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    private String fullName;
    private String nickname;
    private String email;
    private UserTypeEnum userType;

    @FilterableId
    private UUID branchId;

    @FilterableId
    private UUID distributorId;

    @FilterableId
    private UUID departmentId;

    @FilterableId
    private UUID positionId;

    private String jobTitle;
    private String avatarUrl;
    private ThemePreferenceEnum themePreference;
    private String languagePreference;
    private String locale;
    private String timezone;
    private String contactPhone;
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private UUID createdBy;
    private OffsetDateTime updatedAt;
    private UUID updatedBy;
}