package com.banking.api.controller;

import com.banking.api.dto.AvailabilityResponse;
import com.banking.api.dto.ClientResponse;
import com.banking.api.dto.RegisterRequest;
import com.banking.api.service.PublicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final PublicService publicService;

    /**
     * Public registration endpoint - creates a new client
     * This doesn't require authentication
     */
    @PostMapping("/register")
    public ResponseEntity<ClientResponse> register(@Valid @RequestBody RegisterRequest request) {
        log.info("Public registration request for: {}", request.getCompanyName());
        ClientResponse response = publicService.registerClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Check if a username/email is available
     */
    @GetMapping("/check-availability")
    public ResponseEntity<AvailabilityResponse> checkAvailability(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email) {
        return ResponseEntity.ok(publicService.checkAvailability(username, email));
    }
}
