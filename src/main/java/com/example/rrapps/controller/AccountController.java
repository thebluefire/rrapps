package com.example.rrapps.controller;

import com.example.rrapps.entity.Account;
import com.example.rrapps.service.AccountService;
import org.springframework.http.MediaType;
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
class AccountController {

    private final AccountService service;

    AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    Flux<Account> getAll() {
        return this.service.all();
    }

    @GetMapping("/{id}")
    Mono<Account> getById(@PathVariable("id") Long id) {
        return this.service.get(id);
    }

    @PostMapping
    Mono<Account> create(@RequestBody Account account) {
        return this.service.create(account);
    }

    @DeleteMapping("/{id}")
    Mono<Account> deleteById(@PathVariable Long id) {
        return this.service.delete(id);
    }

    @PutMapping("/{id}")
    Mono<Account> updateById(@PathVariable String id, @RequestBody Account account) {
        return this.service.update(account);
    }
}
