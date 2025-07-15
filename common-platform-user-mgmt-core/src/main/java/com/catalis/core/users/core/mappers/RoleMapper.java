package com.catalis.core.users.core.mappers;

import com.catalis.core.users.interfaces.dtos.RoleDTO;
import com.catalis.core.users.models.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for converting between Role entity and RoleDTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {
    
    /**
     * Converts a Role entity to a RoleDTO.
     *
     * @param entity the Role entity
     * @return the RoleDTO
     */
    RoleDTO toDTO(Role entity);
    
    /**
     * Converts a RoleDTO to a Role entity.
     *
     * @param dto the RoleDTO
     * @return the Role entity
     */
    Role toEntity(RoleDTO dto);
}