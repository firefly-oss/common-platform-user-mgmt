package com.firefly.core.users.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.users.core.mappers.UserAccountMapper;
import com.firefly.core.users.interfaces.dtos.UserAccountDTO;
import com.firefly.core.users.models.entities.UserAccount;
import com.firefly.core.users.models.repositories.UserAccountRepository;
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
class UserAccountServiceImplTest {

    @Mock
    private UserAccountRepository repository;

    @Mock
    private UserAccountMapper mapper;

    @InjectMocks
    private UserAccountServiceImpl service;

    private UserAccount userAccount;
    private UserAccountDTO userAccountDTO;
    private FilterRequest<UserAccountDTO> filterRequest;
    private PaginationResponse<UserAccountDTO> paginationResponse;

    @BeforeEach
    void setUp() {
        // Initialize test data
        userAccount = new UserAccount();
        userAccount.setId(1L);

        userAccountDTO = new UserAccountDTO();
        userAccountDTO.setId(1L);

        filterRequest = new FilterRequest<>();

        // We'll mock PaginationResponse since it's from an external library
        paginationResponse = mock(PaginationResponse.class);
    }

    // We're not testing the filterUserAccounts method because it relies on FilterUtils,
    // which is an external library that we don't have access to.
    // The method simply delegates to FilterUtils.createFilter().filter(),
    // so there's not much value in testing it.

    @Test
    void createUserAccount_ShouldCreateAndReturnUserAccount() {
        // Arrange
        when(mapper.toEntity(any(UserAccountDTO.class))).thenReturn(userAccount);
        when(repository.save(any(UserAccount.class))).thenReturn(Mono.just(userAccount));
        when(mapper.toDTO(any(UserAccount.class))).thenReturn(userAccountDTO);

        // Act & Assert
        StepVerifier.create(service.createUserAccount(userAccountDTO))
                .expectNext(userAccountDTO)
                .verifyComplete();

        verify(mapper).toEntity(userAccountDTO);
        verify(repository).save(userAccount);
        verify(mapper).toDTO(userAccount);
    }

    @Test
    void updateUserAccount_WhenUserAccountExists_ShouldUpdateAndReturnUserAccount() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(userAccount));
        when(mapper.toEntity(any(UserAccountDTO.class))).thenReturn(userAccount);
        when(repository.save(any(UserAccount.class))).thenReturn(Mono.just(userAccount));
        when(mapper.toDTO(any(UserAccount.class))).thenReturn(userAccountDTO);

        // Act & Assert
        StepVerifier.create(service.updateUserAccount(1L, userAccountDTO))
                .expectNext(userAccountDTO)
                .verifyComplete();

        verify(repository).findById(1L);
        verify(mapper).toEntity(userAccountDTO);
        verify(repository).save(userAccount);
        verify(mapper).toDTO(userAccount);
    }

    @Test
    void updateUserAccount_WhenUserAccountDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updateUserAccount(1L, userAccountDTO))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("User account not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(mapper, never()).toEntity(any());
        verify(repository, never()).save(any());
        verify(mapper, never()).toDTO(any());
    }

    @Test
    void deleteUserAccount_WhenUserAccountExists_ShouldDeleteUserAccount() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(userAccount));
        when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteUserAccount(1L))
                .verifyComplete();

        verify(repository).findById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteUserAccount_WhenUserAccountDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteUserAccount(1L))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("User account not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    void getUserAccountById_WhenUserAccountExists_ShouldReturnUserAccount() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(userAccount));
        when(mapper.toDTO(any(UserAccount.class))).thenReturn(userAccountDTO);

        // Act & Assert
        StepVerifier.create(service.getUserAccountById(1L))
                .expectNext(userAccountDTO)
                .verifyComplete();

        verify(repository).findById(1L);
        verify(mapper).toDTO(userAccount);
    }

    @Test
    void getUserAccountById_WhenUserAccountDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getUserAccountById(1L))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("User account not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(mapper, never()).toDTO(any());
    }
}
