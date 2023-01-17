package com.example.paymentservice.account;

import com.example.account.api.AccountsApiController;
import com.example.account.model.PaymentRequestBody;
import com.example.account.model.PaymentResponse;
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
public class PaymentInvalidAccountTest {
    @Autowired public TestRestTemplate restTemplate;

    @Test
    // 2
    public void whenInvalidReceiverAccountAndSufficientFunds_thenRejectTransfer() {
        // Before
        BigDecimal beforeBalance222 = TestUtils.getBalance(222, restTemplate).getBody().getBalance();
        BigDecimal beforeBalance111 = TestUtils.getBalance(111, restTemplate).getBody().getBalance();
        BigDecimal transferAmount = BigDecimal.valueOf(10.0);

        PaymentRequestBody paymentRequest = TestUtils.createPaymentRequest(111000, transferAmount);

        // Operate
        ResponseEntity<PaymentResponse> response = TestUtils.performPayment(222, paymentRequest, restTemplate);

        // Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        BigDecimal newBalance111 = TestUtils.getBalance(111, restTemplate).getBody().getBalance();
        BigDecimal newBalance222 = TestUtils.getBalance(222, restTemplate).getBody().getBalance();
        Assertions.assertEquals(beforeBalance111, newBalance111);
        Assertions.assertEquals(beforeBalance222, newBalance222);
    }

}