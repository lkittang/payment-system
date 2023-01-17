package com.example.account.model;

import java.net.URI;
import java.util.Objects;
import com.example.account.model.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * PaymentRequestBody
 */

@JsonTypeName("paymentRequestBody")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-17T00:02:53.553960600+01:00[Europe/Oslo]")
public class PaymentRequestBody {

  @JsonProperty("recipientAccountNumber")
  private Integer recipientAccountNumber;

  @JsonProperty("amount")
  private BigDecimal amount;

  @JsonProperty("currency")
  private Currency currency;

  public PaymentRequestBody recipientAccountNumber(Integer recipientAccountNumber) {
    this.recipientAccountNumber = recipientAccountNumber;
    return this;
  }

  /**
   * Get recipientAccountNumber
   * @return recipientAccountNumber
  */
  
  @Schema(name = "recipientAccountNumber", required = false)
  public Integer getRecipientAccountNumber() {
    return recipientAccountNumber;
  }

  public void setRecipientAccountNumber(Integer recipientAccountNumber) {
    this.recipientAccountNumber = recipientAccountNumber;
  }

  public PaymentRequestBody amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  @Valid 
  @Schema(name = "amount", required = false)
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public PaymentRequestBody currency(Currency currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
  */
  @Valid 
  @Schema(name = "currency", required = false)
  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentRequestBody paymentRequestBody = (PaymentRequestBody) o;
    return Objects.equals(this.recipientAccountNumber, paymentRequestBody.recipientAccountNumber) &&
        Objects.equals(this.amount, paymentRequestBody.amount) &&
        Objects.equals(this.currency, paymentRequestBody.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recipientAccountNumber, amount, currency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentRequestBody {\n");
    sb.append("    recipientAccountNumber: ").append(toIndentedString(recipientAccountNumber)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

