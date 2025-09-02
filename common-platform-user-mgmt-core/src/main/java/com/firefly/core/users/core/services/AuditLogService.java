package com.firefly.core.users.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.users.interfaces.dtos.AuditLogDTO;

import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Service interface for managing audit logs.
 */
public interface AuditLogService {
    /**
     * Filters the audit logs based on the given criteria.
     *
     * @param filterRequest the request object containing filtering criteria for AuditLogDTO
     * @return a reactive {@code Mono} emitting a {@code PaginationResponse} containing the filtered list of audit logs
     */
    Mono<PaginationResponse<AuditLogDTO>> filterAuditLogs(FilterRequest<AuditLogDTO> filterRequest);
    
    /**
     * Creates a new audit log based on the provided information.
     *
     * @param auditLogDTO the DTO object containing details of the audit log to be created
     * @return a Mono that emits the created AuditLogDTO object
     */
    Mono<AuditLogDTO> createAuditLog(AuditLogDTO auditLogDTO);
    
    /**
     * Updates an existing audit log with updated information.
     *
     * @param auditLogId the unique identifier of the audit log to be updated
     * @param auditLogDTO the data transfer object containing the updated details of the audit log
     * @return a reactive Mono containing the updated AuditLogDTO
     */
    Mono<AuditLogDTO> updateAuditLog(UUID auditLogId, AuditLogDTO auditLogDTO);

    /**
     * Deletes an audit log identified by its unique ID.
     *
     * @param auditLogId the unique identifier of the audit log to be deleted
     * @return a Mono that completes when the audit log is successfully deleted or errors if the deletion fails
     */
    Mono<Void> deleteAuditLog(UUID auditLogId);

    /**
     * Retrieves an audit log by its unique identifier.
     *
     * @param auditLogId the unique identifier of the audit log to retrieve
     * @return a Mono emitting the {@link AuditLogDTO} representing the audit log if found,
     *         or an empty Mono if the audit log does not exist
     */
    Mono<AuditLogDTO> getAuditLogById(UUID auditLogId);
}