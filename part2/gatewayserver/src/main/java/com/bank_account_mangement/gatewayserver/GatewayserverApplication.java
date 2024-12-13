package com.bank_account_mangement.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("accounts", r -> r.path("/bankAccountManagement/accounts/**")
                        .filters(f -> f.rewritePath("/bankAccountManagement/accounts/(?<segment>.*)", "/${segment}"))
                        .uri("lb://ACCOUNTS"))

                .route("loans", r -> r.path("/bankAccountManagement/loans/**")
                        .filters(f -> f.rewritePath("/bankAccountManagement/loans/(?<segment>.*)", "/${segment}"))
                        .uri("lb://LOANS"))

                .route("cards", r -> r.path("/bankAccountManagement/cards/**")
                        .filters(f -> f.rewritePath("/bankAccountManagement/cards/(?<segment>.*)", "/${segment}"))
                        .uri("lb://CARDS"))
                .build();
    }

}
