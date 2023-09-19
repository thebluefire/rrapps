package com.example.rrapps.service;

import com.example.rrapps.entity.Account;
import com.example.rrapps.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public Flux<Account> all() {
        return repository.findAll();
    }

    public Mono<Account> get(Long id) {
        return repository.findById(id);
    }

    public Mono<Account> create(Account account) {
        account.setLastOperationTime(LocalDateTime.now());
        return repository.save(account);
    }

    public Mono<Account> update(Long id, Account account) {
        account.setId(id);
        account.setLastOperationTime(LocalDateTime.now());
        return repository
                .findById(account.getId())
                .map(p -> account)
                .flatMap(repository::save);
    }

    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }

}
