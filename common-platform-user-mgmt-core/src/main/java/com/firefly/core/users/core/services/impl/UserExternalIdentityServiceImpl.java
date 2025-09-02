package com.firefly.core.users.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.users.core.mappers.UserExternalIdentityMapper;
import com.firefly.core.users.interfaces.dtos.UserExternalIdentityDTO;
import com.firefly.core.users.models.entities.UserExternalIdentity;
import com.firefly.core.users.models.repositories.UserExternalIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class UserExternalIdentityServiceImpl implements UserExternalIdentityService {

    @Autowired
    private UserExternalIdentityRepository repository;

    @Autowired
    private UserExternalIdentityMapper mapper;

    @Override
    public Mono<PaginationResponse<UserExternalIdentityDTO>> filterUserExternalIdentities(FilterRequest<UserExternalIdentityDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        UserExternalIdentity.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<UserExternalIdentityDTO> createUserExternalIdentity(UserExternalIdentityDTO userExternalIdentityDTO) {
        return Mono.just(userExternalIdentityDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<UserExternalIdentityDTO> updateUserExternalIdentity(UUID userExternalIdentityId, UserExternalIdentityDTO userExternalIdentityDTO) {
        return repository.findById(userExternalIdentityId)
                .switchIfEmpty(Mono.error(new RuntimeException("User external identity not found with ID: " + userExternalIdentityId)))
                .flatMap(existingUserExternalIdentity -> {
                    UserExternalIdentity updatedUserExternalIdentity = mapper.toEntity(userExternalIdentityDTO);
                    updatedUserExternalIdentity.setId(userExternalIdentityId);
                    return repository.save(updatedUserExternalIdentity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteUserExternalIdentity(UUID userExternalIdentityId) {
        return repository.findById(userExternalIdentityId)
                .switchIfEmpty(Mono.error(new RuntimeException("User external identity not found with ID: " + userExternalIdentityId)))
                .flatMap(userExternalIdentity -> repository.deleteById(userExternalIdentityId));
    }

    @Override
    public Mono<UserExternalIdentityDTO> getUserExternalIdentityById(UUID userExternalIdentityId) {
        return repository.findById(userExternalIdentityId)
                .switchIfEmpty(Mono.error(new RuntimeException("User external identity not found with ID: " + userExternalIdentityId)))
                .map(mapper::toDTO);
    }
}