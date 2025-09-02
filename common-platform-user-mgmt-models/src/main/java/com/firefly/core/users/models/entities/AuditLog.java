package com.firefly.core.users.models.entities;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Entity representing an audit log entry in the system.
 * Maps to the audit_log table in the database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("audit_log")
public class AuditLog {

    @Id
    private UUID id;

    @Column("user_account_id")
    private UUID userAccountId;

    private String action;

    private String resource;

    @Column("resource_id")
    private String resourceId;

    private JsonNode metadata;

    @Column("ip_address")
    private String ipAddress;

    private OffsetDateTime timestamp;
}
