package com.example.paymentsystem.repository;

import com.example.account.model.BalanceResponse;
import com.example.account.model.Currency;
import com.example.account.model.NewAccountRequest;
import com.example.account.model.PaymentResponse;
import com.example.paymentsystem.account.Account;
import com.example.paymentsystem.account.AccountDetails;
import com.example.paymentsystem.account.AccountImpl;
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
                performTransaction(source.get().getKey(), recipient.get().getKey(), amount);

                BalanceResponse balanceResponse = new BalanceResponse();
                balanceResponse.setAccountId(source.get().getKey());
                balanceResponse.setCurrency(Currency.valueOf(source.get().getValue().getCurrency()));
                balanceResponse.setBalance(source.get().getValue().getBalance());
                PaymentResponse response = new PaymentResponse();
                response.setNewBalance(balanceResponse);
                return response;
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
