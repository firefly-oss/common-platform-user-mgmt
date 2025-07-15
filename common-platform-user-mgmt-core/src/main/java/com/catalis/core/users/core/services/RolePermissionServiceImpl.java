package com.catalis.core.users.core.services;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.users.core.mappers.RolePermissionMapper;
import com.catalis.core.users.interfaces.dtos.RolePermissionDTO;
import com.catalis.core.users.models.entities.RolePermission;
import com.catalis.core.users.models.repositories.RolePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionRepository repository;

    @Autowired
    private RolePermissionMapper mapper;

    @Override
    public Mono<PaginationResponse<RolePermissionDTO>> filterRolePermissions(FilterRequest<RolePermissionDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        RolePermission.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<RolePermissionDTO> createRolePermission(RolePermissionDTO rolePermissionDTO) {
        return Mono.just(rolePermissionDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RolePermissionDTO> updateRolePermission(Long rolePermissionId, RolePermissionDTO rolePermissionDTO) {
        return repository.findById(rolePermissionId)
                .switchIfEmpty(Mono.error(new RuntimeException("Role-Permission mapping not found with ID: " + rolePermissionId)))
                .flatMap(existingRolePermission -> {
                    RolePermission updatedRolePermission = mapper.toEntity(rolePermissionDTO);
                    updatedRolePermission.setId(rolePermissionId);
                    return repository.save(updatedRolePermission);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteRolePermission(Long rolePermissionId) {
        return repository.findById(rolePermissionId)
                .switchIfEmpty(Mono.error(new RuntimeException("Role-Permission mapping not found with ID: " + rolePermissionId)))
                .flatMap(rolePermission -> repository.deleteById(rolePermissionId));
    }

    @Override
    public Mono<RolePermissionDTO> getRolePermissionById(Long rolePermissionId) {
        return repository.findById(rolePermissionId)
                .switchIfEmpty(Mono.error(new RuntimeException("Role-Permission mapping not found with ID: " + rolePermissionId)))
                .map(mapper::toDTO);
    }
}