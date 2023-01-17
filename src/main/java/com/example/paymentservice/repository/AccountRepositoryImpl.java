package com.example.paymentservice.repository;

import com.example.paymentservice.account.Account;
import com.example.paymentservice.account.AccountDetails;
import com.example.paymentservice.account.AccountImpl;
import com.example.paymentservice.account.AccountMap;
import com.example.paymentservice.model.BalanceResponse;
import com.example.paymentservice.model.Currency;
import com.example.paymentservice.model.NewAccountRequest;
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
        Currency currency = Currency.EUR;
        AccountDetails accountDetails = new AccountDetails(request.getAccountId(), request.getAccountNumber());
        Account account = new AccountImpl(accountDetails, currency.getValue());
        accountMap.getMap().put(request.getAccountId(), account);
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setAccountId(request.getAccountId());
        balanceResponse.setCurrency(currency);
        balanceResponse.setBalance(account.getBalance());
        return balanceResponse;
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
    public void performTransfer(int sourceId, int accountNumber, BigDecimal amount) throws Exception {
        Optional<Map.Entry<Integer, Account>> source = accountMap.getMap().entrySet().stream()
                .filter(entry -> entry.getKey().equals(sourceId))
                .findAny();
        if (source.isPresent()
                && source.get().getValue().getBalance().subtract(amount).doubleValue() >= 0.0) {
            Optional<Map.Entry<Integer, Account>> recipient = accountMap.getMap().entrySet().stream()
                    .filter(entry -> entry.getValue().getDetails().accountNumber() == accountNumber)
                    .findAny();
            if (recipient.isPresent()) {
                performTransaction(source.get().getKey(), recipient.get().getKey(), amount);
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
