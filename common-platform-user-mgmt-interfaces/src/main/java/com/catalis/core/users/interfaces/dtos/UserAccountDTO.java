package com.catalis.core.users.interfaces.dtos;

import com.catalis.core.users.interfaces.enums.ThemePreferenceEnum;
import com.catalis.core.users.interfaces.enums.UserTypeEnum;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * DTO representing a user account in the system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String fullName;
    private String nickname;
    private String email;
    private UserTypeEnum userType;

    @FilterableId
    private Long branchId;

    @FilterableId
    private Long distributorId;

    @FilterableId
    private Long departmentId;

    @FilterableId
    private Long positionId;

    private String jobTitle;
    private String avatarUrl;
    private ThemePreferenceEnum themePreference;
    private String languagePreference;
    private String locale;
    private String timezone;
    private String contactPhone;
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private Long createdBy;
    private OffsetDateTime updatedAt;
    private Long updatedBy;
}