package com.example.paymentsystem.account;

import java.math.BigDecimal;

public interface Account {
    AccountDetails getDetails();
    String getCurrency();
    BigDecimal getBalance();
    void setBalance(BigDecimal newBalance);

}
