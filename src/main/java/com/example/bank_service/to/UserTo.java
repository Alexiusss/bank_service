package com.example.bank_service.to;

import java.time.LocalDate;

public record UserTo(
        String username,
        String password,
        String fullName,
        double startDeposit,
        String phoneNumber,
        String email,
        LocalDate birthDate
) {
}