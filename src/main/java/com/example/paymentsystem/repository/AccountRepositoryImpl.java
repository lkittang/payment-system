package com.example.paymentsystem.repository;

import com.example.account.model.BalanceResponse;
import com.example.account.model.NewAccountRequest;
import com.example.account.model.PaymentResponse;
import com.example.paymentsystem.account.Account;
import com.example.paymentsystem.account.AccountMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired private final AccountMap accountMap;


    @Override
    public BalanceResponse createAccount(NewAccountRequest request) {
        return null;
    }

    @Override
    public void deleteAccount(int accountId) {
        accountMap.getMap().remove(accountId);
    }

    @Override
    public Map<Integer, Account> getAccounts() {
        return accountMap.getMap();
    }

    @Override
    public PaymentResponse performPayment(int sourceId, int accountNumber, BigDecimal amount) throws Exception {
        Optional<Map.Entry<Integer, Account>> source = accountMap.getMap().entrySet().stream()
                .filter(entry -> entry.getKey().equals(sourceId))
                .findAny();
        if (source.isPresent()
                && source.get().getValue().getBalance().subtract(amount).doubleValue() >= 0.0) {
            Optional<Map.Entry<Integer, Account>> recipient = accountMap.getMap().entrySet().stream()
                    .filter(entry -> entry.getValue().getDetails().accountNumber() == accountNumber)
                    .findAny();
            if (recipient.isPresent()) {
                performTransaction(recipient.get().getKey(), source.get().getKey(), amount);
                return new PaymentResponse();
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    private void performTransaction(Integer sourceId, Integer recipientId, BigDecimal amount) {
        Account sourceAccount = accountMap.getMap().get(sourceId);
        BigDecimal originalSourceBalance = sourceAccount.getBalance();
        sourceAccount.setBalance(originalSourceBalance.subtract(amount));

        Account recipientAccount = accountMap.getMap().get(recipientId);
        BigDecimal originalRecipientBalance = recipientAccount.getBalance();
        recipientAccount.setBalance(originalRecipientBalance.add(amount));
    }
}
