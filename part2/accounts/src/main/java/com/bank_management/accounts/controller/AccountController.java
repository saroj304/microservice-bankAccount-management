package com.bank_management.accounts.controller;

import com.bank_management.accounts.constants.AccountConstants;
import com.bank_management.accounts.dto.CustomerDto;
import com.bank_management.accounts.dto.ResponseDto;
import com.bank_management.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {

    private final IAccountService accountService;

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

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(
            @RequestParam @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number format ")

            String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccountDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(customerDto);
    }

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


}
