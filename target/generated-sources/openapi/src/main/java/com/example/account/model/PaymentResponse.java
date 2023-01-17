package com.example.account.model;

import java.net.URI;
import java.util.Objects;
import com.example.account.model.BalanceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * PaymentResponse
 */

@JsonTypeName("paymentResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-17T00:02:53.553960600+01:00[Europe/Oslo]")
public class PaymentResponse {

  @JsonProperty("newBalance")
  private BalanceResponse newBalance;

  public PaymentResponse newBalance(BalanceResponse newBalance) {
    this.newBalance = newBalance;
    return this;
  }

  /**
   * Get newBalance
   * @return newBalance
  */
  @Valid 
  @Schema(name = "newBalance", required = false)
  public BalanceResponse getNewBalance() {
    return newBalance;
  }

  public void setNewBalance(BalanceResponse newBalance) {
    this.newBalance = newBalance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentResponse paymentResponse = (PaymentResponse) o;
    return Objects.equals(this.newBalance, paymentResponse.newBalance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newBalance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentResponse {\n");
    sb.append("    newBalance: ").append(toIndentedString(newBalance)).append("\n");
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

