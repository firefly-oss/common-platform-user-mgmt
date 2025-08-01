package com.catalis.core.users.core.services;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.users.core.mappers.UserExternalIdentityMapper;
import com.catalis.core.users.interfaces.dtos.UserExternalIdentityDTO;
import com.catalis.core.users.models.entities.UserExternalIdentity;
import com.catalis.core.users.models.repositories.UserExternalIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

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
    public Mono<UserExternalIdentityDTO> updateUserExternalIdentity(Long userExternalIdentityId, UserExternalIdentityDTO userExternalIdentityDTO) {
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
    public Mono<Void> deleteUserExternalIdentity(Long userExternalIdentityId) {
        return repository.findById(userExternalIdentityId)
                .switchIfEmpty(Mono.error(new RuntimeException("User external identity not found with ID: " + userExternalIdentityId)))
                .flatMap(userExternalIdentity -> repository.deleteById(userExternalIdentityId));
    }

    @Override
    public Mono<UserExternalIdentityDTO> getUserExternalIdentityById(Long userExternalIdentityId) {
        return repository.findById(userExternalIdentityId)
                .switchIfEmpty(Mono.error(new RuntimeException("User external identity not found with ID: " + userExternalIdentityId)))
                .map(mapper::toDTO);
    }
}