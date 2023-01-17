package com.example.paymentservice.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class AccountMapImpl implements AccountMap{

    private final Map<Integer, Account> map;
    @Override
    public Map<Integer, Account> getMap() {
        return map;
    }
}
