package com.example.paymentservice.account;

import com.example.paymentservice.model.BalanceResponse;
import com.example.paymentservice.model.Currency;
import com.example.paymentservice.model.TransferRequestBody;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public class TestUtils {

    private TestUtils() {}

    static Account createAccount(int accountId, int accountNumber, String currency, double balance) {
        Account account = new AccountImpl(new AccountDetails(accountId, accountNumber), currency);
        account.setBalance(BigDecimal.valueOf(balance));
        return account;
    }

    static TransferRequestBody createPaymentRequest(int recipientAccountNumber, BigDecimal transferAmount) {
        TransferRequestBody paymentRequest = new TransferRequestBody();
        paymentRequest.setRecipientAccountNumber(recipientAccountNumber);
        paymentRequest.setAmount(transferAmount);
        paymentRequest.setCurrency(Currency.EUR);
        return paymentRequest;
    }

    static ResponseEntity<BalanceResponse> getBalance(int accountId, TestRestTemplate restTemplate) {
        return restTemplate.getForEntity("/accounts/" + accountId + "/balance", BalanceResponse.class);
    }

    static ResponseEntity<Void> performPayment(int accountId, TransferRequestBody paymentRequest, TestRestTemplate restTemplate) {
        return restTemplate.postForEntity("/accounts/" + accountId + "/transfer", paymentRequest, Void.class);
    }
}
