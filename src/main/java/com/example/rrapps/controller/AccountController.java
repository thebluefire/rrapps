package com.example.rrapps.controller;

import com.example.rrapps.entity.Account;
import com.example.rrapps.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
class AccountController {

    private final AccountService service;

    @GetMapping
    Flux<Account> getAll() {
        return service.all();
    }

    @GetMapping("/{id}")
    Mono<ResponseEntity<Account>> getById(@PathVariable("id") Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    Mono<ResponseEntity<Account>> create(@RequestBody Account account) {
        return service.create(account)
                .map(acc -> new ResponseEntity<>(acc, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    Mono<ResponseEntity<Account>> updateById(@PathVariable Long id, @RequestBody Account account) {
        return service.update(id, account)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    Mono<ResponseEntity<Object>> deleteById(@PathVariable Long id) {
        return service.delete(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
