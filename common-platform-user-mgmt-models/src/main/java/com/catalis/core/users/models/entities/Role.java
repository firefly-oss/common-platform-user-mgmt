package com.catalis.core.users.models.entities;

import com.catalis.core.users.interfaces.enums.ScopeTypeEnum;
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
    private Long id;
    
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
    private Long createdBy;
    
    @LastModifiedDate
    @Column("updated_at")
    private OffsetDateTime updatedAt;
    
    @Column("updated_by")
    private Long updatedBy;
}