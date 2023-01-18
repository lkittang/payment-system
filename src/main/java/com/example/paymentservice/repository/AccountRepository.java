package com.example.paymentservice.repository;

import com.example.paymentservice.account.Account;

import java.util.Map;

public interface AccountRepository {
    void createAccount(int accountId, Account account);
    void deleteAccount(int accountId);
    Map<Integer, Account> getAccounts();
    void updateAccount(int accountId, Account account);
}
