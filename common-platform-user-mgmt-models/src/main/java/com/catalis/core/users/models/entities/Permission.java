package com.catalis.core.users.models.entities;

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
 * Entity representing a permission in the system.
 * Maps to the permission table in the database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("permission")
public class Permission {
    
    @Id
    private Long id;
    
    private String name;
    
    private String description;
    
    private String domain;
    
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