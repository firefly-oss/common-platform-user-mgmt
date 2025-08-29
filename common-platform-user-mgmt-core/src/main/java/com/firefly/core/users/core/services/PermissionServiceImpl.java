package com.firefly.core.users.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.users.core.mappers.PermissionMapper;
import com.firefly.core.users.interfaces.dtos.PermissionDTO;
import com.firefly.core.users.models.entities.Permission;
import com.firefly.core.users.models.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository repository;

    @Autowired
    private PermissionMapper mapper;

    @Override
    public Mono<PaginationResponse<PermissionDTO>> filterPermissions(FilterRequest<PermissionDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        Permission.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<PermissionDTO> createPermission(PermissionDTO permissionDTO) {
        return Mono.just(permissionDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<PermissionDTO> updatePermission(Long permissionId, PermissionDTO permissionDTO) {
        return repository.findById(permissionId)
                .switchIfEmpty(Mono.error(new RuntimeException("Permission not found with ID: " + permissionId)))
                .flatMap(existingPermission -> {
                    Permission updatedPermission = mapper.toEntity(permissionDTO);
                    updatedPermission.setId(permissionId);
                    return repository.save(updatedPermission);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deletePermission(Long permissionId) {
        return repository.findById(permissionId)
                .switchIfEmpty(Mono.error(new RuntimeException("Permission not found with ID: " + permissionId)))
                .flatMap(permission -> repository.deleteById(permissionId));
    }

    @Override
    public Mono<PermissionDTO> getPermissionById(Long permissionId) {
        return repository.findById(permissionId)
                .switchIfEmpty(Mono.error(new RuntimeException("Permission not found with ID: " + permissionId)))
                .map(mapper::toDTO);
    }
}