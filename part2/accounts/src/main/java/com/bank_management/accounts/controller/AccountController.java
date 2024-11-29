package com.bank_management.accounts.controller;

import com.bank_management.accounts.constants.AccountConstants;
import com.bank_management.accounts.dto.AccountsContactInfoDto;
import com.bank_management.accounts.dto.CustomerDto;
import com.bank_management.accounts.dto.ErrorResponseDto;
import com.bank_management.accounts.dto.ResponseDto;
import com.bank_management.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@Tag(
        name = "Crud Rest Api for accounts ",
        description = "REST API To Create ,Delete, Update, Fetch Account Data"
)
public class AccountController {




    private Environment environment;
    private final IAccountService accountService;

    private AccountsContactInfoDto accountsContactInfoDto;
    @Operation(
            summary = "api to create a new accounts"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Account created successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        accountService.createAccount(customerDto);
        return
                ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(ResponseDto.builder()
                                .statusCode(AccountConstants.STATUS_201)
                                .message(AccountConstants.MESSAGE_201)
                                .build());
    }


    @Operation(
            summary = "api to fetch a particular accounts",
            description = "fetch the details of the user using mobileNumber"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "302",
                    description = "user details is found!!"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(
            @RequestParam @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number format ")

            String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccountDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(customerDto);
    }

    @Operation(
            summary = "api to update a  accounts"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "400",
                    description = "Update operation failed. Account number is required to update user information."
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            )
    })

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean updateResult = accountService.updateAccount(customerDto);
        if (!updateResult) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.builder()
                            .statusCode(AccountConstants.STATUS_400)
                            .message(AccountConstants.MESSAGE_400_UPDATE)
                            .build());

        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseDto
                        .builder()
                        .statusCode(AccountConstants.STATUS_200)
                        .message(AccountConstants.MESSAGE_200)
                        .build());

    }

    /**
     * @param mobileNumber-Input is Mobile Number
     * @return boolean indication the deletion is successful
     */


    @Operation(
            summary = "api to delete user account by mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "400",
                    description = "Delete operation failed. Mobile number is required to update user information."
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number format")
                                                     String mobileNumber) {
        boolean deletionResult = accountService.deleteAccount(mobileNumber);
        if (!deletionResult) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.builder()
                            .statusCode(AccountConstants.STATUS_400)
                            .message(AccountConstants.MESSAGE_400_DELETE)
                            .build());

        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseDto
                        .builder()
                        .statusCode(AccountConstants.STATUS_200)
                        .message(AccountConstants.MESSAGE_200)
                        .build());

    }

    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("build.version"));
    }

    @Operation(
            summary = "Get Java version",
            description = "Get Java versions details that is installed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get Contact Info",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }


}
