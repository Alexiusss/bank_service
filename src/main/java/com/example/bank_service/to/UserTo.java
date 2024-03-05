package com.example.bank_service.to;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.example.bank_service.util.UserUtil.PHONE_NUMBER_PATTERN_MESSAGE;
import static com.example.bank_service.util.UserUtil.PHONE_NUMBER_REGEX;

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
        @Pattern(regexp = PHONE_NUMBER_REGEX, message = PHONE_NUMBER_PATTERN_MESSAGE)
        String phoneNumber,
        @Email
        @NotBlank
        @Size(max = 128)
        String email,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate birthDate
) {
}