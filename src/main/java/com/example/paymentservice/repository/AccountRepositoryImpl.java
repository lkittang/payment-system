package com.example.paymentservice.repository;

import com.example.paymentservice.account.Account;
import com.example.paymentservice.account.AccountMap;
import com.example.paymentservice.account.AccountUtils;
import com.example.paymentservice.model.BalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired private final AccountMap accountMap;

    @Override
    public BalanceResponse createAccount(int accountId, Account account) {
        accountMap.getMap().put(accountId, account);
        return AccountUtils.createBalanceResponse(accountId, account);
    }

    @Override
    public void deleteAccount(int accountId) {
        accountMap.getMap().remove(accountId);
    }

    @Override
    public Map<Integer, Account> getAccounts() {
        return accountMap.getMap();
    }

    @Override
    public void updateAccount(int accountId, Account account) {
        accountMap.getMap().put(accountId, account);
    }

}
