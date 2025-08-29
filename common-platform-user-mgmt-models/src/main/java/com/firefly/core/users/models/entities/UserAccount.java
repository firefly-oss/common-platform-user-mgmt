package com.firefly.core.users.models.entities;

import com.firefly.core.users.interfaces.enums.ThemePreferenceEnum;
import com.firefly.core.users.interfaces.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;

/**
 * Entity representing a user account in the system.
 * Maps to the user_account table in the database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("user_account")
public class UserAccount {
    
    @Id
    private Long id;
    
    @Column("full_name")
    private String fullName;
    
    private String nickname;
    
    private String email;
    
    @Column("user_type")
    private UserTypeEnum userType;
    
    @Column("branch_id")
    private Long branchId;
    
    @Column("distributor_id")
    private Long distributorId;
    
    @Column("department_id")
    private Long departmentId;
    
    @Column("position_id")
    private Long positionId;
    
    @Column("job_title")
    private String jobTitle;
    
    @Column("avatar_url")
    private String avatarUrl;
    
    @Column("theme_preference")
    private ThemePreferenceEnum themePreference;
    
    @Column("language_preference")
    private String languagePreference;
    
    private String locale;
    
    private String timezone;
    
    @Column("contact_phone")
    private String contactPhone;
    
    @Column("is_active")
    private Boolean isActive;
    
    @CreatedDate
    @Column("created_at")
    private OffsetDateTime createdAt;
    
    @Column("created_by")
    private Long createdBy;
    
    @LastModifiedDate
    @Column("updated_at")
    private OffsetDateTime updatedAt;
    
    @Column("updated_by")
    private Long updatedBy;
}