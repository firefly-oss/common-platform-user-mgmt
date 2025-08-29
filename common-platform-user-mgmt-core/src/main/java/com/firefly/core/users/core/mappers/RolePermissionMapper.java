package com.firefly.core.users.core.mappers;

import com.firefly.core.users.interfaces.dtos.RolePermissionDTO;
import com.firefly.core.users.models.entities.RolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for converting between RolePermission entity and RolePermissionDTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RolePermissionMapper {
    
    /**
     * Converts a RolePermission entity to a RolePermissionDTO.
     *
     * @param entity the RolePermission entity
     * @return the RolePermissionDTO
     */
    RolePermissionDTO toDTO(RolePermission entity);
    
    /**
     * Converts a RolePermissionDTO to a RolePermission entity.
     *
     * @param dto the RolePermissionDTO
     * @return the RolePermission entity
     */
    RolePermission toEntity(RolePermissionDTO dto);
}