package com.example.paymentsystem.account;

import com.example.account.api.AccountsApiController;
import com.example.account.model.BalanceResponse;
import com.example.paymentsystem.PaymentServiceApplication;
import com.example.paymentsystem.api.AccountsApiDelegateImpl;
import com.example.paymentsystem.repository.AccountRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PaymentServiceApplication.class
)
@Import(TestConfig.class)
@ContextConfiguration(classes = {AccountsApiController.class, AccountsApiDelegateImpl.class, AccountRepositoryImpl.class})
public class AccountBalanceTest {
    @Autowired public TestRestTemplate restTemplate;

    @Test
    // 4
    public void whenValidAccountDetails_thenReturnBalance() {
        ResponseEntity<BalanceResponse> response = AccountUtils.getBalance(111, restTemplate);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(BigDecimal.valueOf(100.0), response.getBody().getBalance());
    }

    @Test
    // 5
    public void whenInvalidAccountDetails_thenRejectBalance() {
        ResponseEntity<BalanceResponse> response = AccountUtils.getBalance(999, restTemplate);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
