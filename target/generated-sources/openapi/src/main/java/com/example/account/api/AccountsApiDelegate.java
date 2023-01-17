package com.example.account.api;

import com.example.account.model.AccountDetails;
import com.example.account.model.BalanceResponse;
import com.example.account.model.NewAccountRequest;
import com.example.account.model.PaymentRequestBody;
import com.example.account.model.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link AccountsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-17T00:02:53.553960600+01:00[Europe/Oslo]")
public interface AccountsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /accounts
     * Create new account
     *
     * @param newAccountRequest New account payload (optional)
     * @return OK (status code 200)
     *         or Invalid accountId. Account already exists (status code 400)
     * @see AccountsApi#createNewAccount
     */
    default ResponseEntity<BalanceResponse> createNewAccount(NewAccountRequest newAccountRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"accountId\" : 0, \"balance\" : 6.027456183070403 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /accounts/{accountId}
     * Delete account
     *
     * @param accountId Id of account (required)
     * @return Account deleted (status code 204)
     *         or Account not found (status code 404)
     * @see AccountsApi#deleteAccount
     */
    default ResponseEntity<Void> deleteAccount(Integer accountId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /accounts/{accountId}/balance
     * Get account balance
     *
     * @param accountId Id of account (required)
     * @return OK (status code 200)
     *         or Account not found (status code 404)
     * @see AccountsApi#getAccountBalance
     */
    default ResponseEntity<BalanceResponse> getAccountBalance(Integer accountId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"accountId\" : 0, \"balance\" : 6.027456183070403 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /accounts/{accountId}/details
     * Get account details
     *
     * @param accountId Id of account (required)
     * @return OK (status code 200)
     *         or Account not found (status code 404)
     * @see AccountsApi#getAccountDetails
     */
    default ResponseEntity<AccountDetails> getAccountDetails(Integer accountId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"accountId\" : 0, \"accountNumber\" : 6 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /accounts/{accountId}/payment
     * Perform payment
     *
     * @param accountId Id of account (required)
     * @param paymentRequestBody New payment payload (optional)
     * @return OK (status code 200)
     *         or Invalid recipient account (status code 404)
     *         or Insufficient funds (status code 500)
     * @see AccountsApi#performPayment
     */
    default ResponseEntity<PaymentResponse> performPayment(Integer accountId,
        PaymentRequestBody paymentRequestBody) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"newBalance\" : { \"accountId\" : 0, \"balance\" : 6.027456183070403 } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
