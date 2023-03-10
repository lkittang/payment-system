package com.example.paymentservice.account;

import com.example.paymentservice.api.AccountsApiController;
import com.example.paymentservice.model.BalanceResponse;
import com.example.paymentservice.PaymentServiceApplication;
import com.example.paymentservice.api.AccountService;
import com.example.paymentservice.repository.AccountRepositoryImpl;
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
@ContextConfiguration(classes = {AccountsApiController.class, AccountService.class, AccountRepositoryImpl.class})
public class AccountBalanceTest {
    @Autowired public TestRestTemplate restTemplate;

    @Test
    // 4
    public void whenValidAccountDetails_thenReturnBalance() {
        ResponseEntity<BalanceResponse> response = TestUtils.getBalance(111, restTemplate);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(BigDecimal.valueOf(100.0), response.getBody().getBalance());
    }

    @Test
    // 5
    public void whenInvalidAccountDetails_thenRejectBalance() {
        ResponseEntity<BalanceResponse> response = TestUtils.getBalance(999, restTemplate);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
