package com.firefly.core.users.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.users.core.mappers.RoleMapper;
import com.firefly.core.users.core.services.RoleService;
import com.firefly.core.users.interfaces.dtos.RoleDTO;
import com.firefly.core.users.models.entities.Role;
import com.firefly.core.users.models.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private RoleMapper mapper;

    @Override
    public Mono<PaginationResponse<RoleDTO>> filterRoles(FilterRequest<RoleDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        Role.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<RoleDTO> createRole(RoleDTO roleDTO) {
        return Mono.just(roleDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RoleDTO> updateRole(UUID roleId, RoleDTO roleDTO) {
        return repository.findById(roleId)
                .switchIfEmpty(Mono.error(new RuntimeException("Role not found with ID: " + roleId)))
                .flatMap(existingRole -> {
                    Role updatedRole = mapper.toEntity(roleDTO);
                    updatedRole.setId(roleId);
                    return repository.save(updatedRole);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteRole(UUID roleId) {
        return repository.findById(roleId)
                .switchIfEmpty(Mono.error(new RuntimeException("Role not found with ID: " + roleId)))
                .flatMap(role -> repository.deleteById(roleId));
    }

    @Override
    public Mono<RoleDTO> getRoleById(UUID roleId) {
        return repository.findById(roleId)
                .switchIfEmpty(Mono.error(new RuntimeException("Role not found with ID: " + roleId)))
                .map(mapper::toDTO);
    }
}