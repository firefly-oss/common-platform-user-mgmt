package com.catalis.core.users.core.services;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.users.core.mappers.UserExternalIdentityMapper;
import com.catalis.core.users.interfaces.dtos.UserExternalIdentityDTO;
import com.catalis.core.users.models.entities.UserExternalIdentity;
import com.catalis.core.users.models.repositories.UserExternalIdentityRepository;
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
class UserExternalIdentityServiceImplTest {

    @Mock
    private UserExternalIdentityRepository repository;

    @Mock
    private UserExternalIdentityMapper mapper;

    @InjectMocks
    private UserExternalIdentityServiceImpl service;

    private UserExternalIdentity userExternalIdentity;
    private UserExternalIdentityDTO userExternalIdentityDTO;
    private FilterRequest<UserExternalIdentityDTO> filterRequest;
    private PaginationResponse<UserExternalIdentityDTO> paginationResponse;

    @BeforeEach
    void setUp() {
        // Initialize test data
        userExternalIdentity = new UserExternalIdentity();
        userExternalIdentity.setId(1L);

        userExternalIdentityDTO = new UserExternalIdentityDTO();
        userExternalIdentityDTO.setId(1L);

        filterRequest = new FilterRequest<>();

        // We'll mock PaginationResponse since it's from an external library
        paginationResponse = mock(PaginationResponse.class);
    }

    // We're not testing the filterUserExternalIdentities method because it relies on FilterUtils,
    // which is an external library that we don't have access to.
    // The method simply delegates to FilterUtils.createFilter().filter(),
    // so there's not much value in testing it.

    @Test
    void createUserExternalIdentity_ShouldCreateAndReturnUserExternalIdentity() {
        // Arrange
        when(mapper.toEntity(any(UserExternalIdentityDTO.class))).thenReturn(userExternalIdentity);
        when(repository.save(any(UserExternalIdentity.class))).thenReturn(Mono.just(userExternalIdentity));
        when(mapper.toDTO(any(UserExternalIdentity.class))).thenReturn(userExternalIdentityDTO);

        // Act & Assert
        StepVerifier.create(service.createUserExternalIdentity(userExternalIdentityDTO))
                .expectNext(userExternalIdentityDTO)
                .verifyComplete();

        verify(mapper).toEntity(userExternalIdentityDTO);
        verify(repository).save(userExternalIdentity);
        verify(mapper).toDTO(userExternalIdentity);
    }

    @Test
    void updateUserExternalIdentity_WhenUserExternalIdentityExists_ShouldUpdateAndReturnUserExternalIdentity() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(userExternalIdentity));
        when(mapper.toEntity(any(UserExternalIdentityDTO.class))).thenReturn(userExternalIdentity);
        when(repository.save(any(UserExternalIdentity.class))).thenReturn(Mono.just(userExternalIdentity));
        when(mapper.toDTO(any(UserExternalIdentity.class))).thenReturn(userExternalIdentityDTO);

        // Act & Assert
        StepVerifier.create(service.updateUserExternalIdentity(1L, userExternalIdentityDTO))
                .expectNext(userExternalIdentityDTO)
                .verifyComplete();

        verify(repository).findById(1L);
        verify(mapper).toEntity(userExternalIdentityDTO);
        verify(repository).save(userExternalIdentity);
        verify(mapper).toDTO(userExternalIdentity);
    }

    @Test
    void updateUserExternalIdentity_WhenUserExternalIdentityDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updateUserExternalIdentity(1L, userExternalIdentityDTO))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("User external identity not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(mapper, never()).toEntity(any());
        verify(repository, never()).save(any());
        verify(mapper, never()).toDTO(any());
    }

    @Test
    void deleteUserExternalIdentity_WhenUserExternalIdentityExists_ShouldDeleteUserExternalIdentity() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(userExternalIdentity));
        when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteUserExternalIdentity(1L))
                .verifyComplete();

        verify(repository).findById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteUserExternalIdentity_WhenUserExternalIdentityDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteUserExternalIdentity(1L))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("User external identity not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    void getUserExternalIdentityById_WhenUserExternalIdentityExists_ShouldReturnUserExternalIdentity() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(userExternalIdentity));
        when(mapper.toDTO(any(UserExternalIdentity.class))).thenReturn(userExternalIdentityDTO);

        // Act & Assert
        StepVerifier.create(service.getUserExternalIdentityById(1L))
                .expectNext(userExternalIdentityDTO)
                .verifyComplete();

        verify(repository).findById(1L);
        verify(mapper).toDTO(userExternalIdentity);
    }

    @Test
    void getUserExternalIdentityById_WhenUserExternalIdentityDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getUserExternalIdentityById(1L))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("User external identity not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(mapper, never()).toDTO(any());
    }
}
