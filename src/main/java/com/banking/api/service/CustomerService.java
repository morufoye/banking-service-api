package com.banking.api.service;

import com.banking.api.dto.CustomerCreateRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class CustomerService {

    private final WebClient webClient;

    public CustomerService(@Qualifier("customerWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public boolean createCustomer(CustomerCreateRequest request) {
        log.info("Creating customer with fullname: {}", request.getFullname());
        return webClient
                .post()
                .uri("/api/v1/createcust")
                .bodyValue(request)
                .retrieve()
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .onErrorResume(WebClientResponseException.class, ex -> {
                    log.error("Create customer request failed: {}", ex.getMessage());
                    return Mono.just(false);
                })
                .blockOptional()
                .orElse(false);
    }
}
