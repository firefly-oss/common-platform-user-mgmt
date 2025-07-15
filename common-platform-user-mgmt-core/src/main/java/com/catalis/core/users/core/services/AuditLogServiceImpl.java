package com.catalis.core.users.core.services;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.users.core.mappers.AuditLogMapper;
import com.catalis.core.users.interfaces.dtos.AuditLogDTO;
import com.catalis.core.users.models.entities.AuditLog;
import com.catalis.core.users.models.repositories.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogRepository repository;

    @Autowired
    private AuditLogMapper mapper;

    @Override
    public Mono<PaginationResponse<AuditLogDTO>> filterAuditLogs(FilterRequest<AuditLogDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        AuditLog.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<AuditLogDTO> createAuditLog(AuditLogDTO auditLogDTO) {
        return Mono.just(auditLogDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AuditLogDTO> updateAuditLog(Long auditLogId, AuditLogDTO auditLogDTO) {
        return repository.findById(auditLogId)
                .switchIfEmpty(Mono.error(new RuntimeException("Audit log not found with ID: " + auditLogId)))
                .flatMap(existingAuditLog -> {
                    AuditLog updatedAuditLog = mapper.toEntity(auditLogDTO);
                    updatedAuditLog.setId(auditLogId);
                    return repository.save(updatedAuditLog);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteAuditLog(Long auditLogId) {
        return repository.findById(auditLogId)
                .switchIfEmpty(Mono.error(new RuntimeException("Audit log not found with ID: " + auditLogId)))
                .flatMap(auditLog -> repository.deleteById(auditLogId));
    }

    @Override
    public Mono<AuditLogDTO> getAuditLogById(Long auditLogId) {
        return repository.findById(auditLogId)
                .switchIfEmpty(Mono.error(new RuntimeException("Audit log not found with ID: " + auditLogId)))
                .map(mapper::toDTO);
    }
}