package com.firefly.core.users.core.mappers;

import com.firefly.core.users.interfaces.dtos.UserRoleDTO;
import com.firefly.core.users.models.entities.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for converting between UserRole entity and UserRoleDTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper {
    
    /**
     * Converts a UserRole entity to a UserRoleDTO.
     *
     * @param entity the UserRole entity
     * @return the UserRoleDTO
     */
    UserRoleDTO toDTO(UserRole entity);
    
    /**
     * Converts a UserRoleDTO to a UserRole entity.
     *
     * @param dto the UserRoleDTO
     * @return the UserRole entity
     */
    UserRole toEntity(UserRoleDTO dto);
}