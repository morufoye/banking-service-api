package com.banking.api.dto;

import com.banking.api.entity.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    private UUID id;
    private String contactEmail;
    private String companyName;
    private String registrationNumber;
    private String contactFirstName;
    private String contactLastName;
    private String contactPhone;
    private String country;
    private String address;
    private String taxId;
    private String companyAddress;
    private String industry;
    private String phoneNumber;
    private ClientStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime approvedAt;
    private Integer userCount;
}