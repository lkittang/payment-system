package com.example.paymentsystem.account;

import com.example.account.model.Currency;
import com.example.account.model.PaymentRequestBody;

import java.math.BigDecimal;

public class AccountUtils {
    static Account createAccount(int accountId, int accountNumber, String currency, double balance) {
        Account account = new AccountImpl(new AccountDetails(accountId, accountNumber), currency);
        account.setBalance(BigDecimal.valueOf(balance));
        return account;
    }

    static PaymentRequestBody createPaymentRequest(int recipientAccountNumber, BigDecimal transferAmount) {
        PaymentRequestBody paymentRequest = new PaymentRequestBody();
        paymentRequest.setRecipientAccountNumber(recipientAccountNumber);
        paymentRequest.setAmount(transferAmount);
        paymentRequest.setCurrency(Currency.EUR);
        return paymentRequest;
    }
}
