package com.catalis.core.users.core.services;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.users.core.mappers.RolePermissionMapper;
import com.catalis.core.users.interfaces.dtos.RolePermissionDTO;
import com.catalis.core.users.models.entities.RolePermission;
import com.catalis.core.users.models.repositories.RolePermissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RolePermissionServiceImplTest {

    @Mock
    private RolePermissionRepository repository;

    @Mock
    private RolePermissionMapper mapper;

    @InjectMocks
    private RolePermissionServiceImpl service;

    private RolePermission rolePermission;
    private RolePermissionDTO rolePermissionDTO;
    private FilterRequest<RolePermissionDTO> filterRequest;
    private PaginationResponse<RolePermissionDTO> paginationResponse;

    @BeforeEach
    void setUp() {
        // Initialize test data
        rolePermission = new RolePermission();
        rolePermission.setId(1L);

        rolePermissionDTO = new RolePermissionDTO();
        rolePermissionDTO.setId(1L);

        filterRequest = new FilterRequest<>();

        // We'll mock PaginationResponse since it's from an external library
        paginationResponse = mock(PaginationResponse.class);
    }

    // We're not testing the filterRolePermissions method because it relies on FilterUtils,
    // which is an external library that we don't have access to.
    // The method simply delegates to FilterUtils.createFilter().filter(),
    // so there's not much value in testing it.

    @Test
    void createRolePermission_ShouldCreateAndReturnRolePermission() {
        // Arrange
        when(mapper.toEntity(any(RolePermissionDTO.class))).thenReturn(rolePermission);
        when(repository.save(any(RolePermission.class))).thenReturn(Mono.just(rolePermission));
        when(mapper.toDTO(any(RolePermission.class))).thenReturn(rolePermissionDTO);

        // Act & Assert
        StepVerifier.create(service.createRolePermission(rolePermissionDTO))
                .expectNext(rolePermissionDTO)
                .verifyComplete();

        verify(mapper).toEntity(rolePermissionDTO);
        verify(repository).save(rolePermission);
        verify(mapper).toDTO(rolePermission);
    }

    @Test
    void updateRolePermission_WhenRolePermissionExists_ShouldUpdateAndReturnRolePermission() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(rolePermission));
        when(mapper.toEntity(any(RolePermissionDTO.class))).thenReturn(rolePermission);
        when(repository.save(any(RolePermission.class))).thenReturn(Mono.just(rolePermission));
        when(mapper.toDTO(any(RolePermission.class))).thenReturn(rolePermissionDTO);

        // Act & Assert
        StepVerifier.create(service.updateRolePermission(1L, rolePermissionDTO))
                .expectNext(rolePermissionDTO)
                .verifyComplete();

        verify(repository).findById(1L);
        verify(mapper).toEntity(rolePermissionDTO);
        verify(repository).save(rolePermission);
        verify(mapper).toDTO(rolePermission);
    }

    @Test
    void updateRolePermission_WhenRolePermissionDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updateRolePermission(1L, rolePermissionDTO))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Role-Permission mapping not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(mapper, never()).toEntity(any());
        verify(repository, never()).save(any());
        verify(mapper, never()).toDTO(any());
    }

    @Test
    void deleteRolePermission_WhenRolePermissionExists_ShouldDeleteRolePermission() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(rolePermission));
        when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteRolePermission(1L))
                .verifyComplete();

        verify(repository).findById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteRolePermission_WhenRolePermissionDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteRolePermission(1L))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Role-Permission mapping not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    void getRolePermissionById_WhenRolePermissionExists_ShouldReturnRolePermission() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(rolePermission));
        when(mapper.toDTO(any(RolePermission.class))).thenReturn(rolePermissionDTO);

        // Act & Assert
        StepVerifier.create(service.getRolePermissionById(1L))
                .expectNext(rolePermissionDTO)
                .verifyComplete();

        verify(repository).findById(1L);
        verify(mapper).toDTO(rolePermission);
    }

    @Test
    void getRolePermissionById_WhenRolePermissionDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getRolePermissionById(1L))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Role-Permission mapping not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(mapper, never()).toDTO(any());
    }
}
