package com.example.account;

import com.example.account.api.AccountsApiDelegate;
import com.example.account.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountsApiDelegateImpl implements AccountsApiDelegate {
    @Override
    public ResponseEntity<BalanceResponse> createNewAccount(NewAccountRequest newAccountRequest) {
        System.out.println("POST /");
        return AccountsApiDelegate.super.createNewAccount(newAccountRequest);
    }

    @Override
    public ResponseEntity<Void> deleteAccount(String accountId) {
        System.out.println("DELETE /" + accountId);
        return AccountsApiDelegate.super.deleteAccount(accountId);
    }

    @Override
    public ResponseEntity<BalanceResponse> getAccountBalance(String accountId) {
        System.out.println("GET /" + accountId + "/balance");
        return AccountsApiDelegate.super.getAccountBalance(accountId);
    }

    @Override
    public ResponseEntity<AccountDetails> getAccountDetails(String accountId) {
        System.out.println("GET /" + accountId);
        return AccountsApiDelegate.super.getAccountDetails(accountId);
    }

    @Override
    public ResponseEntity<PaymentResponse> performPayment(String accountId, PaymentRequestBody paymentRequestBody) {
        System.out.println("POST /"+ accountId + "/payment");
        return AccountsApiDelegate.super.performPayment(accountId, paymentRequestBody);
    }
}

