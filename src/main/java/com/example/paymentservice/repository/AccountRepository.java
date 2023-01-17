package com.example.paymentservice.repository;

import com.example.paymentservice.account.Account;
import com.example.paymentservice.model.BalanceResponse;
import com.example.paymentservice.model.NewAccountRequest;

import java.math.BigDecimal;
import java.util.Map;

public interface AccountRepository {
    BalanceResponse createAccount(NewAccountRequest request);
    void deleteAccount(int accountId);
    Map<Integer, Account> getAccounts();
    void performTransfer(int sourceId, int accountNumber, BigDecimal amount) throws Exception;
}
