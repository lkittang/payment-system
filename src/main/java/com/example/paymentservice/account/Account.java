package com.example.paymentservice.account;

import com.example.paymentservice.model.Currency;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class Account {
    private final int accountNumber;
    private final Currency currency;
    private BigDecimal balance = BigDecimal.valueOf(0.0);

    public void setBalance(BigDecimal newBalance) {
        this.balance = newBalance;
    }
}
