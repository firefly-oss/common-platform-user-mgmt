package com.firefly.core.users.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.users.core.mappers.AuditLogMapper;
import com.firefly.core.users.interfaces.dtos.AuditLogDTO;
import com.firefly.core.users.models.entities.AuditLog;
import com.firefly.core.users.models.repositories.AuditLogRepository;
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

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuditLogServiceImplTest {

    @Mock
    private AuditLogRepository repository;

    @Mock
    private AuditLogMapper mapper;

    @InjectMocks
    private AuditLogServiceImpl service;

    private AuditLog auditLog;
    private AuditLogDTO auditLogDTO;
    private FilterRequest<AuditLogDTO> filterRequest;
    private PaginationResponse<AuditLogDTO> paginationResponse;

    @BeforeEach
    void setUp() {
        // Initialize test data
        auditLog = new AuditLog();
        auditLog.setId(1L);

        auditLogDTO = new AuditLogDTO();
        auditLogDTO.setId(1L);

        filterRequest = new FilterRequest<>();

        // We'll mock PaginationResponse since it's from an external library
        paginationResponse = mock(PaginationResponse.class);
    }

    // We're not testing the filterAuditLogs method because it relies on FilterUtils,
    // which is an external library that we don't have access to.
    // The method simply delegates to FilterUtils.createFilter().filter(),
    // so there's not much value in testing it.

    @Test
    void createAuditLog_ShouldCreateAndReturnAuditLog() {
        // Arrange
        when(mapper.toEntity(any(AuditLogDTO.class))).thenReturn(auditLog);
        when(repository.save(any(AuditLog.class))).thenReturn(Mono.just(auditLog));
        when(mapper.toDTO(any(AuditLog.class))).thenReturn(auditLogDTO);

        // Act & Assert
        StepVerifier.create(service.createAuditLog(auditLogDTO))
                .expectNext(auditLogDTO)
                .verifyComplete();

        verify(mapper).toEntity(auditLogDTO);
        verify(repository).save(auditLog);
        verify(mapper).toDTO(auditLog);
    }

    @Test
    void updateAuditLog_WhenAuditLogExists_ShouldUpdateAndReturnAuditLog() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(auditLog));
        when(mapper.toEntity(any(AuditLogDTO.class))).thenReturn(auditLog);
        when(repository.save(any(AuditLog.class))).thenReturn(Mono.just(auditLog));
        when(mapper.toDTO(any(AuditLog.class))).thenReturn(auditLogDTO);

        // Act & Assert
        StepVerifier.create(service.updateAuditLog(1L, auditLogDTO))
                .expectNext(auditLogDTO)
                .verifyComplete();

        verify(repository).findById(1L);
        verify(mapper).toEntity(auditLogDTO);
        verify(repository).save(auditLog);
        verify(mapper).toDTO(auditLog);
    }

    @Test
    void updateAuditLog_WhenAuditLogDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updateAuditLog(1L, auditLogDTO))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Audit log not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(mapper, never()).toEntity(any());
        verify(repository, never()).save(any());
        verify(mapper, never()).toDTO(any());
    }

    @Test
    void deleteAuditLog_WhenAuditLogExists_ShouldDeleteAuditLog() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(auditLog));
        when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteAuditLog(1L))
                .verifyComplete();

        verify(repository).findById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteAuditLog_WhenAuditLogDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteAuditLog(1L))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Audit log not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    void getAuditLogById_WhenAuditLogExists_ShouldReturnAuditLog() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(auditLog));
        when(mapper.toDTO(any(AuditLog.class))).thenReturn(auditLogDTO);

        // Act & Assert
        StepVerifier.create(service.getAuditLogById(1L))
                .expectNext(auditLogDTO)
                .verifyComplete();

        verify(repository).findById(1L);
        verify(mapper).toDTO(auditLog);
    }

    @Test
    void getAuditLogById_WhenAuditLogDoesNotExist_ShouldReturnError() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getAuditLogById(1L))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Audit log not found with ID: 1"))
                .verify();

        verify(repository).findById(1L);
        verify(mapper, never()).toDTO(any());
    }
}
