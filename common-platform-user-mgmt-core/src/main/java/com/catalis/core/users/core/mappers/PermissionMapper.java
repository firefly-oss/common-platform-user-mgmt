package com.catalis.core.users.core.mappers;

import com.catalis.core.users.interfaces.dtos.PermissionDTO;
import com.catalis.core.users.models.entities.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for converting between Permission entity and PermissionDTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {
    
    /**
     * Converts a Permission entity to a PermissionDTO.
     *
     * @param entity the Permission entity
     * @return the PermissionDTO
     */
    PermissionDTO toDTO(Permission entity);
    
    /**
     * Converts a PermissionDTO to a Permission entity.
     *
     * @param dto the PermissionDTO
     * @return the Permission entity
     */
    Permission toEntity(PermissionDTO dto);
}