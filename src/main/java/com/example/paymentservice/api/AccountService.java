package com.example.paymentservice.api;

import com.example.paymentservice.account.Account;
import com.example.paymentservice.account.AccountUtils;
import com.example.paymentservice.model.AccountDetails;
import com.example.paymentservice.model.BalanceResponse;
import com.example.paymentservice.model.NewAccountRequest;
import com.example.paymentservice.model.TransferRequestBody;
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
public class AccountService implements AccountsApiDelegate {

    @Autowired private final AccountRepository accountRepository;

    @Override
    public ResponseEntity<BalanceResponse> createNewAccount(NewAccountRequest newAccountRequest) {
        System.out.println("POST /");
        boolean isAccountExists = accountRepository.getAccounts().entrySet().stream()
                .anyMatch(accountEntry -> accountEntry.getKey().equals(newAccountRequest.getAccountId()));
        if (isAccountExists) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Account account = new Account(newAccountRequest.getAccountNumber(), newAccountRequest.getCurrency());
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
            return ResponseEntity.ok(AccountUtils.createBalanceResponse(accountId, accountEntry.get().getValue()));
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
    public ResponseEntity<Void> performTransfer(Integer sourceId, TransferRequestBody paymentRequestBody) {
        Account sourceAccount = accountRepository.getAccounts().get(sourceId);
        BigDecimal amount = paymentRequestBody.getAmount();
        if (sourceAccount.getBalance().subtract(amount).doubleValue() < 0.0) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Map.Entry<Integer, Account>> recipientOptional = accountRepository.getAccounts().entrySet()
                .stream()
                .filter(entry -> entry.getValue().getAccountNumber() == paymentRequestBody.getRecipientAccountNumber())
                .findAny();
        if (recipientOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            Account recipientAccount = recipientOptional.get().getValue();
            sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
            recipientAccount.setBalance(recipientAccount.getBalance().add(amount));
            return ResponseEntity.ok().build();
        }
    }

}

