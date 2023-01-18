package com.example.paymentservice.repository;

import com.example.paymentservice.account.Account;
import com.example.paymentservice.model.BalanceResponse;

import java.util.Map;

public interface AccountRepository {
    BalanceResponse createAccount(int accountId, Account request);
    void deleteAccount(int accountId);
    Map<Integer, Account> getAccounts();
    void updateAccount(int accountId, Account account);
}
