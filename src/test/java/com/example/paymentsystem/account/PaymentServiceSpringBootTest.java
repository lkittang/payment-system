package com.example.paymentsystem.account;

import com.example.account.api.AccountsApiController;
import com.example.account.model.BalanceResponse;
import com.example.account.model.PaymentRequestBody;
import com.example.account.model.PaymentResponse;
import com.example.paymentsystem.PaymentServiceApplication;
import com.example.paymentsystem.api.AccountsApiDelegateImpl;
import com.example.paymentsystem.repository.AccountRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PaymentServiceApplication.class
)
@Import(PaymentServiceSpringBootTest.StandardTestConfig.class)
@ContextConfiguration(classes = {AccountsApiController.class, AccountsApiDelegateImpl.class, AccountRepositoryImpl.class})
public class PaymentServiceSpringBootTest {
    @Autowired public TestRestTemplate restTemplate;

    @Test
    public void whenValidAccountDetails_thenReturnBalance() {
        ResponseEntity<BalanceResponse> response = getBalance(111);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(BigDecimal.valueOf(100.0), response.getBody().getBalance());
    }

    @Test
    public void whenInvalidAccountDetails_thenRejectBalance() {
        ResponseEntity<BalanceResponse> response = getBalance(999);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void whenValidDetailsAndSufficientFunds_thenCreditAndDebitAccountsCorrectly() {
        // Before
        BigDecimal beforeBalance111 = getBalance(111).getBody().getBalance();
        BigDecimal beforeBalance222 = getBalance(222).getBody().getBalance();
        BigDecimal transferAmount = BigDecimal.valueOf(10.0);

        PaymentRequestBody paymentRequest = AccountUtils.createPaymentRequest(222, transferAmount);

        // Operate
        ResponseEntity<PaymentResponse> response = restTemplate.postForEntity("/accounts/111/payment", paymentRequest, PaymentResponse.class);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        BigDecimal newBalance111 = getBalance(111).getBody().getBalance();
        BigDecimal newBalance222 = getBalance(222).getBody().getBalance();
        Assertions.assertEquals(beforeBalance111.subtract(transferAmount), newBalance111);
        Assertions.assertEquals(beforeBalance222.add(transferAmount), newBalance222);
    }

    private ResponseEntity<BalanceResponse> getBalance(int accountId) {
        return restTemplate.getForEntity("/accounts/" + accountId + "/balance", BalanceResponse.class);
    }
    @TestConfiguration
    public static class StandardTestConfig {
        @Bean
        public AccountMap accountMap() {
            Map<Integer, Account> accountMap = new HashMap<>();

            Account account111 = AccountUtils.createAccount(111, 111000, "EUR", 100.0);
            accountMap.put(111, account111);

            Account account222 = AccountUtils.createAccount(222, 222000, "EUR", 100.0);
            accountMap.put(222, account222);

            return new AccountMapImpl(accountMap);
        }
    }

}
