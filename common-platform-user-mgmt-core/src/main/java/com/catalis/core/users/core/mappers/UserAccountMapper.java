package com.catalis.core.users.core.mappers;

import com.catalis.core.users.interfaces.dtos.UserAccountDTO;
import com.catalis.core.users.models.entities.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for converting between UserAccount entity and UserAccountDTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserAccountMapper {
    
    /**
     * Converts a UserAccount entity to a UserAccountDTO.
     *
     * @param entity the UserAccount entity
     * @return the UserAccountDTO
     */
    UserAccountDTO toDTO(UserAccount entity);
    
    /**
     * Converts a UserAccountDTO to a UserAccount entity.
     *
     * @param dto the UserAccountDTO
     * @return the UserAccount entity
     */
    UserAccount toEntity(UserAccountDTO dto);
}