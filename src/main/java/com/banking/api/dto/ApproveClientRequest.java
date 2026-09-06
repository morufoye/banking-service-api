package com.banking.api.dto;
import java.util.List;

public record ApproveClientRequest(
        List<String> approvedRoles
) {
}



