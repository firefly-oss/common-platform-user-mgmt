package com.firefly.core.users.models.entities;

import com.firefly.core.users.interfaces.enums.ScopeTypeEnum;
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
 * Entity representing a role in the system.
 * Maps to the role table in the database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("role")
public class Role {
    
    @Id
    private UUID id;

    private String name;

    private String description;

    @Column("is_assignable")
    private Boolean isAssignable;

    @Column("scope_type")
    private ScopeTypeEnum scopeType;

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