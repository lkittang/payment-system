package com.example.paymentservice.account;

import com.example.paymentservice.api.AccountsApiController;
import com.example.paymentservice.model.BalanceResponse;
import com.example.paymentservice.model.NewAccountRequest;
import com.example.paymentservice.PaymentServiceApplication;
import com.example.paymentservice.api.AccountsApiDelegateImpl;
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
@ContextConfiguration(classes = {AccountsApiController.class, AccountsApiDelegateImpl.class, AccountRepositoryImpl.class})
public class CreateAccountTest {
    @Autowired public TestRestTemplate restTemplate;

    @Test
    public void whenCreatingNotExistingAccount_thenRetrieveBalance() {
        // Before
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, TestUtils.getBalance(333, restTemplate).getStatusCode());

        // Operate
        NewAccountRequest newAccountRequest = new NewAccountRequest();
        newAccountRequest.setAccountId(333);
        newAccountRequest.setAccountNumber(333000);
        ResponseEntity<BalanceResponse> response = restTemplate.postForEntity("/accounts", newAccountRequest, BalanceResponse.class);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(333, response.getBody().getAccountId());
        Assertions.assertEquals(BigDecimal.valueOf(0.0), response.getBody().getBalance());
    }

}
