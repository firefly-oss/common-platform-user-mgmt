package com.catalis.core.users.core.mappers;

import com.catalis.core.users.interfaces.dtos.AuditLogDTO;
import com.catalis.core.users.models.entities.AuditLog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for converting between AuditLog entity and AuditLogDTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuditLogMapper {
    
    /**
     * Converts an AuditLog entity to an AuditLogDTO.
     *
     * @param entity the AuditLog entity
     * @return the AuditLogDTO
     */
    AuditLogDTO toDTO(AuditLog entity);
    
    /**
     * Converts an AuditLogDTO to an AuditLog entity.
     *
     * @param dto the AuditLogDTO
     * @return the AuditLog entity
     */
    AuditLog toEntity(AuditLogDTO dto);
}