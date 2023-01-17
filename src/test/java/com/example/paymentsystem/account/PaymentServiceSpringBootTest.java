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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

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
    public void testGetBalance() {
        ResponseEntity<BalanceResponse> response = restTemplate.getForEntity("/accounts/111/balance", BalanceResponse.class);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
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
