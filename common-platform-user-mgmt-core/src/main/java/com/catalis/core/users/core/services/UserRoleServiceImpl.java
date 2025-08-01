package com.catalis.core.users.core.services;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.users.core.mappers.UserRoleMapper;
import com.catalis.core.users.interfaces.dtos.UserRoleDTO;
import com.catalis.core.users.models.entities.UserRole;
import com.catalis.core.users.models.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository repository;

    @Autowired
    private UserRoleMapper mapper;

    @Override
    public Mono<PaginationResponse<UserRoleDTO>> filterUserRoles(FilterRequest<UserRoleDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        UserRole.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<UserRoleDTO> createUserRole(UserRoleDTO userRoleDTO) {
        return Mono.just(userRoleDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<UserRoleDTO> updateUserRole(Long userRoleId, UserRoleDTO userRoleDTO) {
        return repository.findById(userRoleId)
                .switchIfEmpty(Mono.error(new RuntimeException("User role not found with ID: " + userRoleId)))
                .flatMap(existingUserRole -> {
                    UserRole updatedUserRole = mapper.toEntity(userRoleDTO);
                    updatedUserRole.setId(userRoleId);
                    return repository.save(updatedUserRole);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteUserRole(Long userRoleId) {
        return repository.findById(userRoleId)
                .switchIfEmpty(Mono.error(new RuntimeException("User role not found with ID: " + userRoleId)))
                .flatMap(userRole -> repository.deleteById(userRoleId));
    }

    @Override
    public Mono<UserRoleDTO> getUserRoleById(Long userRoleId) {
        return repository.findById(userRoleId)
                .switchIfEmpty(Mono.error(new RuntimeException("User role not found with ID: " + userRoleId)))
                .map(mapper::toDTO);
    }
}