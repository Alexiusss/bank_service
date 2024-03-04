package com.example.bank_service.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record TransferTo(
        @NotBlank
        String toUserId,
        @Positive
        double amount
) {
}