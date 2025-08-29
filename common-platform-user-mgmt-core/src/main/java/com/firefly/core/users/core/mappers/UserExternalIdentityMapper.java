package com.firefly.core.users.core.mappers;

import com.firefly.core.users.interfaces.dtos.UserExternalIdentityDTO;
import com.firefly.core.users.models.entities.UserExternalIdentity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for converting between UserExternalIdentity entity and UserExternalIdentityDTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserExternalIdentityMapper {
    
    /**
     * Converts a UserExternalIdentity entity to a UserExternalIdentityDTO.
     *
     * @param entity the UserExternalIdentity entity
     * @return the UserExternalIdentityDTO
     */
    UserExternalIdentityDTO toDTO(UserExternalIdentity entity);
    
    /**
     * Converts a UserExternalIdentityDTO to a UserExternalIdentity entity.
     *
     * @param dto the UserExternalIdentityDTO
     * @return the UserExternalIdentity entity
     */
    UserExternalIdentity toEntity(UserExternalIdentityDTO dto);
}