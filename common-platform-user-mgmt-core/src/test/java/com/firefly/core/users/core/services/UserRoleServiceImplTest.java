package com.firefly.core.users.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.users.core.mappers.UserRoleMapper;
import com.firefly.core.users.interfaces.dtos.UserRoleDTO;
import com.firefly.core.users.models.entities.UserRole;
import com.firefly.core.users.models.repositories.UserRoleRepository;
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
class UserRoleServiceImplTest {

    @Mock
    private UserRoleRepository repository;

    @Mock
    private UserRoleMapper mapper;

    @InjectMocks
    private UserRoleServiceImpl service;

    private UserRole userRole;
    private UserRoleDTO userRoleDTO;
    private FilterRequest<UserRoleDTO> filterRequest;
    private PaginationResponse<UserRoleDTO> paginationResponse;

    @BeforeEach
    void setUp() {
        // Initialize test data
        userRole = new UserRole();
        userRole.setId(1L);

        userRoleDTO = new UserRoleDTO();
        userRoleDTO.setId(1L);

        filterRequest = new FilterRequest<>();

        // We'll mock PaginationResponse since it's from an external library
        paginationResponse = mock(PaginationResponse.class);
    }

    // We're not testing the filterUserRoles method because it relies on FilterUtils,
    // which is an external library that we don't have access to.
    // The method simply delegates to FilterUtils.createFilter().filter(),
    // so there's not much value in testing it.

    @Test
    void createUserRole_ShouldCreateAndReturnUserRole() {
        // Arrange
        when(mapper.toEntity(any(UserRoleDTO.class))).thenReturn(userRole);
        when(repository.save(any(UserRole.class))).thenReturn(Mono.just(userRole));
        when(mapper.toDTO(any(UserRole.class))).thenReturn(userRoleDTO);

        // Act & Assert
        StepVerifier.create(service.createUserRole(userRoleDTO))
                .expectNext(userRoleDTO)
                .verifyComplete();

        verify(mapper).toEntity(userRoleDTO);
        verify(repository).save(userRole);
        verify(mapper).toDTO(userRole);
    }

    @Test
    void updateUserRole_WhenUserRoleExists_ShouldUpdateAndReturnUserRole() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(userRole));
        when(mapper.toEntity(any(UserRoleDTO.class))).thenReturn(userRole);
        when(repository.save(any(UserRole.class))).thenReturn(Mono.just(userRole));
        when(mapper.toDTO(any(UserRole.class))).thenReturn(userRoleDTO);

        // Act & Assert
        StepVerifier.create(service.updateUserRole(1L, userRoleDTO))
                .expectNext(userRoleDTO)
                .verifyComplete();

        verify(repository).findById(1L);
        verify(mapper).toEntity(userRoleDTO);
        verify(repository).save(userRole);
        verify(mapper).toDTO(userRole);
    }

    @Test
    void updateUserRole_WhenUserRoleDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updateUserRole(1L, userRoleDTO))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("User role not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(mapper, never()).toEntity(any());
        verify(repository, never()).save(any());
        verify(mapper, never()).toDTO(any());
    }

    @Test
    void deleteUserRole_WhenUserRoleExists_ShouldDeleteUserRole() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(userRole));
        when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteUserRole(1L))
                .verifyComplete();

        verify(repository).findById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteUserRole_WhenUserRoleDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteUserRole(1L))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("User role not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    void getUserRoleById_WhenUserRoleExists_ShouldReturnUserRole() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(userRole));
        when(mapper.toDTO(any(UserRole.class))).thenReturn(userRoleDTO);

        // Act & Assert
        StepVerifier.create(service.getUserRoleById(1L))
                .expectNext(userRoleDTO)
                .verifyComplete();

        verify(repository).findById(1L);
        verify(mapper).toDTO(userRole);
    }

    @Test
    void getUserRoleById_WhenUserRoleDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getUserRoleById(1L))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("User role not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(mapper, never()).toDTO(any());
    }
}
