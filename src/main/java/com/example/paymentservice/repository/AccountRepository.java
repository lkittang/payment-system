package com.example.paymentservice.repository;

import com.example.paymentservice.account.Account;
import com.example.paymentservice.model.BalanceResponse;

import java.math.BigDecimal;
import java.util.Map;

public interface AccountRepository {
    BalanceResponse createAccount(int accountId, Account request);
    void deleteAccount(int accountId);
    Map<Integer, Account> getAccounts();
    void performTransfer(int sourceId, int accountNumber, BigDecimal amount) throws Exception;
}
