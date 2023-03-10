package com.example.paymentservice.account;

import com.example.paymentservice.PaymentServiceApplication;
import com.example.paymentservice.api.AccountsApiController;
import com.example.paymentservice.api.AccountService;
import com.example.paymentservice.model.TransferRequestBody;
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
public class PaymentSuccessTest {
    @Autowired public TestRestTemplate restTemplate;

    @Test
    // 1
    public void whenValidDetailsAndSufficientFunds_thenCreditAndDebitAccountsCorrectly() {
        // Before
        BigDecimal beforeBalance111 = TestUtils.getBalance(111, restTemplate).getBody().getBalance();
        BigDecimal beforeBalance222 = TestUtils.getBalance(222, restTemplate).getBody().getBalance();
        BigDecimal transferAmount = BigDecimal.valueOf(10.0);

        TransferRequestBody paymentRequest = TestUtils.createPaymentRequest(222000, transferAmount);

        // Operate
        ResponseEntity<Void> response = TestUtils.performPayment(111, paymentRequest, restTemplate);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        BigDecimal newBalance111 = TestUtils.getBalance(111, restTemplate).getBody().getBalance();
        BigDecimal newBalance222 = TestUtils.getBalance(222, restTemplate).getBody().getBalance();
        Assertions.assertEquals(beforeBalance111.subtract(transferAmount), newBalance111);
        Assertions.assertEquals(beforeBalance222.add(transferAmount), newBalance222);
    }

}
