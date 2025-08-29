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
 * Entity representing a role-permission mapping in the system.
 * Maps to the role_permission table in the database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("role_permission")
public class RolePermission {
    
    @Id
    private Long id;
    
    @Column("role_id")
    private Long roleId;
    
    @Column("permission_id")
    private Long permissionId;
    
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