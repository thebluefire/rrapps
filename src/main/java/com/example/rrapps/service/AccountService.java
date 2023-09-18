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
        return this.repository.findAll();
    }

    public Mono<Account> get(Long id) {
        return this.repository.findById(id);
    }

    public Mono<Account> update(Account account) {
        account.setLastOperationTime(LocalDateTime.now());
        return this.repository
                .findById(account.getId())
                .map(p -> account)
                .flatMap(this.repository::save);
    }

    public Mono<Account> delete(Long id) {
        return this.repository.findById(id);
    }

    public Mono<Account> create(Account account) {
        account.setLastOperationTime(LocalDateTime.now());
        return this.repository.save(account);
    }

}
