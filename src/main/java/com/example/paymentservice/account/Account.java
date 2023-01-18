package com.example.paymentservice.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class Account {
    private final AccountDetails details;
    private final String currency;
    private BigDecimal balance = BigDecimal.valueOf(0.0);

    public void setBalance(BigDecimal newBalance) {
        this.balance = newBalance;
    }
}
