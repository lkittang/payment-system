package com.example.paymentservice.account;

import com.example.paymentservice.model.Currency;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@TestConfiguration
public class TestConfig {
    @Bean
    public AccountMap accountMap() {
        Map<Integer, Account> accountMap = new HashMap<>();

        Account account111 = TestUtils.createAccount(111000, Currency.EUR, 100.0);
        accountMap.put(111, account111);

        Account account222 = TestUtils.createAccount(222000, Currency.EUR, 0.0);
        accountMap.put(222, account222);

        return new AccountMapImpl(accountMap);
    }
}
