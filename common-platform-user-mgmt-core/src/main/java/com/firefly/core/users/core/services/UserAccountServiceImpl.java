package com.firefly.core.users.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.users.core.mappers.UserAccountMapper;
import com.firefly.core.users.interfaces.dtos.UserAccountDTO;
import com.firefly.core.users.models.entities.UserAccount;
import com.firefly.core.users.models.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository repository;

    @Autowired
    private UserAccountMapper mapper;

    @Override
    public Mono<PaginationResponse<UserAccountDTO>> filterUserAccounts(FilterRequest<UserAccountDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        UserAccount.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<UserAccountDTO> createUserAccount(UserAccountDTO userAccountDTO) {
        return Mono.just(userAccountDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<UserAccountDTO> updateUserAccount(Long userAccountId, UserAccountDTO userAccountDTO) {
        return repository.findById(userAccountId)
                .switchIfEmpty(Mono.error(new RuntimeException("User account not found with ID: " + userAccountId)))
                .flatMap(existingUserAccount -> {
                    UserAccount updatedUserAccount = mapper.toEntity(userAccountDTO);
                    updatedUserAccount.setId(userAccountId);
                    return repository.save(updatedUserAccount);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteUserAccount(Long userAccountId) {
        return repository.findById(userAccountId)
                .switchIfEmpty(Mono.error(new RuntimeException("User account not found with ID: " + userAccountId)))
                .flatMap(userAccount -> repository.deleteById(userAccountId));
    }

    @Override
    public Mono<UserAccountDTO> getUserAccountById(Long userAccountId) {
        return repository.findById(userAccountId)
                .switchIfEmpty(Mono.error(new RuntimeException("User account not found with ID: " + userAccountId)))
                .map(mapper::toDTO);
    }
}