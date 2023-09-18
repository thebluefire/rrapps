package com.example.rrapps.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Account {

    @Id
    private Long id;

    private String accountNumber;

    private BigDecimal balance;

    private String currency;

    private LocalDateTime lastOperationTime;

}
