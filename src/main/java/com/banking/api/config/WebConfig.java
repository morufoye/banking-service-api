package com.banking.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

    @Value("${customer.service.url}")
    private String customerServiceUrl;

    @Value("${account.service.url}")
    private String accountServiceUrl;


    @Value("${customer.service.max-buffer-size}")
    private Integer maxBufferSize;

    private String mediaType = MediaType.APPLICATION_JSON_VALUE;

    @Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    WebClient customerWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(customerServiceUrl)
                .exchangeStrategies(ExchangeStrategies
                        .builder()
                        .codecs(codecs -> codecs
                                .defaultCodecs()
                                .maxInMemorySize(maxBufferSize * 1024))
                        .build())
                .defaultHeader("Accept", mediaType)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
