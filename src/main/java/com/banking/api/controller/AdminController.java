package com.banking.api.controller;

import com.banking.api.dto.ClientResponse;
import com.banking.api.entity.ClientStatus;
import com.banking.api.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientResponse>> getAllClients(Authentication authentication) {
        // Extract user info from JWT if needed
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = jwt.getClaim("sub");
        String email = jwt.getClaim("email");

        log.info("Admin {} accessing all clients", email);
        return ResponseEntity.ok(adminService.getAllClients());
    }

    @GetMapping("/clients/pending")
    public ResponseEntity<List<ClientResponse>> getPendingClients() {
        return ResponseEntity.ok(adminService.getClientsByStatus(ClientStatus.PENDING));
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<ClientResponse> getClient(@PathVariable UUID clientId) {
        return ResponseEntity.ok(adminService.getClientById(clientId));
    }

    @PutMapping("/clients/{clientId}/approve")
    public ResponseEntity<ClientResponse> approveClient(@PathVariable UUID clientId) {
        log.info("Approving client: {}", clientId);
        return ResponseEntity.ok(adminService.approveClient(clientId));
    }

    @PutMapping("/clients/{clientId}/reject")
    public ResponseEntity<ClientResponse> rejectClient(@PathVariable UUID clientId) {
        log.info("Rejecting client: {}", clientId);
        return ResponseEntity.ok(adminService.rejectClient(clientId));
    }

    @PutMapping("/clients/{clientId}/suspend")
    public ResponseEntity<ClientResponse> suspendClient(@PathVariable UUID clientId) {
        log.info("Suspending client: {}", clientId);
        return ResponseEntity.ok(adminService.suspendClient(clientId));
    }

    @PutMapping("/clients/{clientId}/activate")
    public ResponseEntity<ClientResponse> activateClient(@PathVariable UUID clientId) {
        log.info("Activating client: {}", clientId);
        return ResponseEntity.ok(adminService.activateClient(clientId));
    }

    @PostMapping("/clients/{clientId}/api-keys")
    public ResponseEntity<?> generateApiKey(
            @PathVariable UUID clientId,
            @RequestParam String description) {
        log.info("Generating API key for client: {}", clientId);
        return ResponseEntity.ok(adminService.generateApiKey(clientId, description));
    }

    @GetMapping("/clients/{clientId}/api-keys")
    public ResponseEntity<?> getClientApiKeys(@PathVariable UUID clientId) {
        return ResponseEntity.ok(adminService.getClientApiKeys(clientId));
    }

    @DeleteMapping("/api-keys/{keyId}")
    public ResponseEntity<Void> revokeApiKey(@PathVariable UUID keyId) {
        log.info("Revoking API key: {}", keyId);
        adminService.revokeApiKey(keyId);
        return ResponseEntity.noContent().build();
    }
}