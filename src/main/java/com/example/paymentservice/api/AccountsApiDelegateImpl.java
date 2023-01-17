package com.example.paymentservice.api;

import com.example.account.api.AccountsApiDelegate;
import com.example.account.model.*;
import com.example.paymentservice.account.Account;
import com.example.paymentservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountsApiDelegateImpl implements AccountsApiDelegate {

    @Autowired private final AccountRepository accountRepository;

    @Override
    public ResponseEntity<BalanceResponse> createNewAccount(NewAccountRequest newAccountRequest) {
        System.out.println("POST /");
        Optional<Map.Entry<Integer, Account>> accountExists = accountRepository.getAccounts().entrySet().stream()
                .filter(accountEntry -> accountEntry.getValue().getDetails().id() == newAccountRequest.getAccountId())
                .findAny();
        if (accountExists.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(accountRepository.createAccount(newAccountRequest));
        }
    }

    @Override
    public ResponseEntity<Void> deleteAccount(Integer accountId) {
        System.out.println("DELETE /" + accountId);
        return AccountsApiDelegate.super.deleteAccount(accountId);
    }

    @Override
    public ResponseEntity<BalanceResponse> getAccountBalance(Integer accountId) {
        System.out.println("GET /" + accountId + "/balance");
        Optional<Map.Entry<Integer, Account>> accountEntry = accountRepository.getAccounts().entrySet().stream()
                .filter(entry -> entry.getKey().equals(accountId))
                .findAny();
        if (accountEntry.isPresent()) {
            Account account = accountEntry.get().getValue();
            BalanceResponse balanceResponse = new BalanceResponse();
            balanceResponse.setAccountId(accountId);
            balanceResponse.setBalance(account.getBalance());
            balanceResponse.setCurrency(Currency.fromValue(account.getCurrency()));
            return ResponseEntity.ok(balanceResponse);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<AccountDetails> getAccountDetails(Integer accountId) {
        System.out.println("GET /" + accountId);
        return AccountsApiDelegate.super.getAccountDetails(accountId);
    }

    @Override
    public ResponseEntity<PaymentResponse> performPayment(Integer accountId, PaymentRequestBody paymentRequestBody) {
        System.out.println("POST /"+ accountId + "/payment");
        Integer recipientAccountNumber = paymentRequestBody.getRecipientAccountNumber();
        BigDecimal amount = paymentRequestBody.getAmount();
        try {
            PaymentResponse response = accountRepository.performPayment(accountId, recipientAccountNumber, amount);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

