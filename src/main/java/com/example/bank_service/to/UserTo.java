package com.example.bank_service.to;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UserTo(
        @NotBlank
        @Size(min = 5, max = 128)
        String username,
        @NotBlank
        @Size(min = 5, max = 32)
        String password,
        @NotBlank
        @Size(min = 5, max = 128)
        String fullName,
        @Positive
        double startDeposit,
        @Size(min = 10, max = 20)
        @Pattern(regexp = "([+]*[0-9]{1,4}\\s?[(]*\\d[0-9]{2,4}[)]*\\s?\\d{3}[-]*\\d{2}[-]*\\d{2})",
                message = "Please fill the phone number in format +1 (234) 567-89-10")
        String phoneNumber,
        @Email
        @NotBlank
        @Size(max = 128)
        String email,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate birthDate
) {
}