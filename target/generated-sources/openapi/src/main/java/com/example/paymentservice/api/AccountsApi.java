/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.2.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.example.paymentservice.api;

import com.example.paymentservice.model.AccountDetails;
import com.example.paymentservice.model.BalanceResponse;
import com.example.paymentservice.model.NewAccountRequest;
import com.example.paymentservice.model.TransferRequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-18T07:42:08.315748700+01:00[Europe/Oslo]")
@Validated
@Tag(name = "accounts", description = "the accounts API")
@RequestMapping("${openapi.paymentService.base-path:}")
public interface AccountsApi {

    default AccountsApiDelegate getDelegate() {
        return new AccountsApiDelegate() {};
    }

    /**
     * POST /accounts
     * Create new account
     *
     * @param newAccountRequest New account payload (optional)
     * @return OK (status code 200)
     *         or Invalid accountId. Account already exists (status code 400)
     */
    @Operation(
        operationId = "createNewAccount",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = BalanceResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid accountId. Account already exists")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/accounts",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<BalanceResponse> createNewAccount(
        @Parameter(name = "NewAccountRequest", description = "New account payload") @Valid @RequestBody(required = false) NewAccountRequest newAccountRequest
    ) {
        return getDelegate().createNewAccount(newAccountRequest);
    }


    /**
     * DELETE /accounts/{accountId}
     * Delete account
     *
     * @param accountId Id of account (required)
     * @return Account deleted (status code 204)
     *         or Account not found (status code 404)
     */
    @Operation(
        operationId = "deleteAccount",
        responses = {
            @ApiResponse(responseCode = "204", description = "Account deleted"),
            @ApiResponse(responseCode = "404", description = "Account not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/accounts/{accountId}"
    )
    default ResponseEntity<Void> deleteAccount(
        @Parameter(name = "accountId", description = "Id of account", required = true) @PathVariable("accountId") Integer accountId
    ) {
        return getDelegate().deleteAccount(accountId);
    }


    /**
     * GET /accounts/{accountId}/balance
     * Get account balance
     *
     * @param accountId Id of account (required)
     * @return OK (status code 200)
     *         or Account not found (status code 404)
     */
    @Operation(
        operationId = "getAccountBalance",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = BalanceResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Account not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/accounts/{accountId}/balance",
        produces = { "application/json" }
    )
    default ResponseEntity<BalanceResponse> getAccountBalance(
        @Parameter(name = "accountId", description = "Id of account", required = true) @PathVariable("accountId") Integer accountId
    ) {
        return getDelegate().getAccountBalance(accountId);
    }


    /**
     * GET /accounts/{accountId}/details
     * Get account details
     *
     * @param accountId Id of account (required)
     * @return OK (status code 200)
     *         or Account not found (status code 404)
     */
    @Operation(
        operationId = "getAccountDetails",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDetails.class))
            }),
            @ApiResponse(responseCode = "404", description = "Account not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/accounts/{accountId}/details",
        produces = { "application/json" }
    )
    default ResponseEntity<AccountDetails> getAccountDetails(
        @Parameter(name = "accountId", description = "Id of account", required = true) @PathVariable("accountId") Integer accountId
    ) {
        return getDelegate().getAccountDetails(accountId);
    }


    /**
     * POST /accounts/{accountId}/transfer
     * Perform transfer
     *
     * @param accountId Id of account (required)
     * @param transferRequestBody New transfer payload (optional)
     * @return OK (status code 200)
     *         or Invalid recipient account (status code 404)
     *         or Insufficient funds (status code 500)
     */
    @Operation(
        operationId = "performTransfer",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Invalid recipient account"),
            @ApiResponse(responseCode = "500", description = "Insufficient funds")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/accounts/{accountId}/transfer",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> performTransfer(
        @Parameter(name = "accountId", description = "Id of account", required = true) @PathVariable("accountId") Integer accountId,
        @Parameter(name = "TransferRequestBody", description = "New transfer payload") @Valid @RequestBody(required = false) TransferRequestBody transferRequestBody
    ) {
        return getDelegate().performTransfer(accountId, transferRequestBody);
    }

}
