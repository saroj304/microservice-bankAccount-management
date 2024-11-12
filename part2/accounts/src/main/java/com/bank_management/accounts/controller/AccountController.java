package com.bank_management.accounts.controller;

import com.bank_management.accounts.constants.AccountConstants;
import com.bank_management.accounts.dto.CustomerDto;
import com.bank_management.accounts.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {
@PostMapping("/")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
     return
          ResponseEntity
                  .status(HttpStatus.CREATED)
                  .body(ResponseDto.builder()
                          .statusCode(AccountConstants.STATUS_201)
                          .message(AccountConstants.MESSAGE_201)
                          .build());
    }
}
