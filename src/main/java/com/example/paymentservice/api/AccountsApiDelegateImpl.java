package com.example.paymentservice.api;

import com.example.paymentservice.account.Account;
import com.example.paymentservice.model.*;
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
        boolean isAccountExists = accountRepository.getAccounts().entrySet().stream()
                .anyMatch(accountEntry -> accountEntry.getKey().equals(newAccountRequest.getAccountId()));
        if (isAccountExists) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Account account = new Account(newAccountRequest.getAccountNumber(), Currency.EUR.getValue());
            return ResponseEntity.ok(accountRepository.createAccount(newAccountRequest.getAccountId(), account));
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
            return ResponseEntity.ok(createBalanceResponse(accountId, accountEntry.get().getValue()));
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
    public ResponseEntity<Void> performTransfer(Integer accountId, TransferRequestBody paymentRequestBody) {
        Integer recipientAccountNumber = paymentRequestBody.getRecipientAccountNumber();
        BigDecimal amount = paymentRequestBody.getAmount();
        try {
            accountRepository.performTransfer(accountId, recipientAccountNumber, amount);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private static BalanceResponse createBalanceResponse(Integer accountId, Account account) {
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setAccountId(accountId);
        balanceResponse.setBalance(account.getBalance());
        balanceResponse.setCurrency(Currency.fromValue(account.getCurrency()));
        return balanceResponse;
    }
}

