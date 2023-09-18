package com.example.rrapps.repository;

import com.example.rrapps.entity.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountRepository extends ReactiveCrudRepository<Account, Long> {

}
