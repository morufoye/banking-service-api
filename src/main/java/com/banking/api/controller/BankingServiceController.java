package com.banking.api.controller;

import com.banking.api.dto.ClientResponse;
import com.banking.api.dto.CustomerCreateRequest;
import com.banking.api.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('ROLE_CLIENT')")
public class BankingServiceController {

    private final CustomerService customerService;

    @PostMapping("/create-customer")
    public ResponseEntity<?> CreateCustomer (@RequestBody CustomerCreateRequest request) {
        boolean response = customerService.createCustomer(request);
        return ResponseEntity.ok().body(response);
    }

}
