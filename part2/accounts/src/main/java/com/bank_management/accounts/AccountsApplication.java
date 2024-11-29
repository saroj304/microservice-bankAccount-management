package com.bank_management.accounts;

import com.bank_management.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(info = @Info(
        title = "Account Microservice REST API documentation",
        description = "bank account management Microservice REST API documentation",
        version = "v1"  ,
        contact = @Contact(
                name = "saroj Khatiwada",
                email = "sarojkhatiwada1999@gmail.com"
        ),
        license = @License(
                url = "https://github.com/saroj304/microservice-bankAccount-management/tree/master"
        )
),
externalDocs = @ExternalDocumentation(
        description = "Bank Account Management Microservice REST API Documentation",
        url = "https://github.com/saroj304/microservice-bankAccount-management/tree/master"
)
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
