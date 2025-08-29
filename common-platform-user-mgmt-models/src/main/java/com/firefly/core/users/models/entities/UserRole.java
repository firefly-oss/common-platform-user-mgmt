package com.firefly.core.users.models.entities;

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
 * Entity representing a user-role assignment in the system.
 * Maps to the user_role table in the database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("user_role")
public class UserRole {
    
    @Id
    private Long id;
    
    @Column("user_account_id")
    private Long userAccountId;
    
    @Column("role_id")
    private Long roleId;
    
    @Column("branch_id")
    private Long branchId;
    
    @Column("distributor_id")
    private Long distributorId;
    
    @Column("assigned_at")
    private OffsetDateTime assignedAt;
    
    @Column("assigned_by")
    private Long assignedBy;
    
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