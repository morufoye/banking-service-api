package com.banking.api.dto;

import com.banking.api.validation.PasswordValidator;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    // === Contact Information ===

    @NotBlank(message = "{validation.contactEmail.required}")
    @Email(message = "{validation.contactEmail.invalid}")
    @Size(max = 100, message = "{validation.contactEmail.size}")
    private String contactEmail;


    @NotBlank(message = "{validation.contactPerson.required}")
    @Size(max = 100, message = "{validation.contactPerson.size}")
    private String contactFirstName;

    @NotBlank(message = "{validation.contactPerson.required}")
    @Size(max = 100, message = "{validation.contactPerson.size}")
    private String contactLastName;

    @NotBlank(message = "{validation.contactPhone.required}")
    @Pattern(
            regexp = "^\\+?[1-9][0-9]{1,14}$|^[0-9\\s\\-\\(\\)]{7,20}$",
            message = "{validation.contactPhone.invalid}"
    )
    @Size(max = 20, message = "{validation.contactPhone.size}")
    private String contactPhone;

    // === Company Information ===

    @NotBlank(message = "{validation.companyName.required}")
    @Size(max = 100, message = "{validation.companyName.size}")
    private String companyName;

    @NotBlank(message = "{validation.registrationNumber.required}")
    @Size(max = 50, message = "{validation.registrationNumber.size}")
    private String registrationNumber;

    //@NotBlank(message = "{validation.country.required}")
    //@Size(max = 100, message = "{validation.country.size}")
    private String country;

//    @NotBlank(message = "{validation.address.required}")
//    @Size(max = 255, message = "{validation.address.size}")
//    private String address;

    @NotBlank(message = "{validation.taxId.required}")
    @Size(max = 50, message = "{validation.taxId.size}")
    private String taxId;

    @NotBlank(message = "{validation.companyAddress.required}")
    @Size(max = 255, message = "{validation.companyAddress.size}")
    private String companyAddress;

    @NotBlank(message = "{validation.industry.required}")
    @Size(max = 50, message = "{validation.industry.size}")
    private String industry;

    @NotBlank(message = "{validation.phoneNumber.required}")
    @Pattern(
            regexp = "^\\+?[1-9][0-9]{1,14}$|^[0-9\\s\\-\\(\\)]{7,20}$",
            message = "{validation.phoneNumber.invalid}"
    )
    @Size(max = 20, message = "{validation.phoneNumber.size}")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
//    @PasswordValidator(
//            minLength = 8,
//            maxLength = 100
//            requireDigit = true,
//            requireLowercase = true,
//            requireUppercase = true,
//            requireSpecialChar = true
//    )
    private String password;
}