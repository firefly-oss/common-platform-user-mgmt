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
import java.util.UUID;

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
    private UUID id;

    @Column("user_account_id")
    private UUID userAccountId;

    @Column("role_id")
    private UUID roleId;

    @Column("branch_id")
    private UUID branchId;

    @Column("distributor_id")
    private UUID distributorId;

    @Column("assigned_at")
    private OffsetDateTime assignedAt;

    @Column("assigned_by")
    private UUID assignedBy;
    
    @CreatedDate
    @Column("created_at")
    private OffsetDateTime createdAt;

    @Column("created_by")
    private UUID createdBy;

    @LastModifiedDate
    @Column("updated_at")
    private OffsetDateTime updatedAt;

    @Column("updated_by")
    private UUID updatedBy;
}