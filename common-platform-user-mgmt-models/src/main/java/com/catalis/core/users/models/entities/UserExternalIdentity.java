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
 * Entity representing an external identity linked to a user account.
 * Maps to the user_external_identity table in the database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("user_external_identity")
public class UserExternalIdentity {
    
    @Id
    private Long id;
    
    @Column("user_account_id")
    private Long userAccountId;
    
    private String provider;
    
    @Column("subject_id")
    private String subjectId;
    
    private String email;
    
    @Column("is_primary")
    private Boolean isPrimary;
    
    @Column("linked_at")
    private OffsetDateTime linkedAt;
    
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