package com.example.paymentservice.repository;

import com.example.paymentservice.account.Account;
import com.example.account.model.BalanceResponse;
import com.example.account.model.NewAccountRequest;
import com.example.account.model.PaymentResponse;

import java.math.BigDecimal;
import java.util.Map;

public interface AccountRepository {
    BalanceResponse createAccount(NewAccountRequest request);
    void deleteAccount(int accountId);
    Map<Integer, Account> getAccounts();
    PaymentResponse performPayment(int sourceId, int accountNumber, BigDecimal amount) throws Exception;
}
