package com.example.paymentservice.account;

import com.example.paymentservice.model.BalanceResponse;

public class AccountUtils {
    public static BalanceResponse createBalanceResponse(Integer accountId, Account account) {
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setAccountId(accountId);
        balanceResponse.setBalance(account.getBalance());
        balanceResponse.setCurrency(account.getCurrency());
        return balanceResponse;
    }
}
