package com.example.bank_service.to;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import static com.example.bank_service.UserUtil.PHONE_NUMBER_PATTERN_MESSAGE;
import static com.example.bank_service.UserUtil.PHONE_NUMBER_REGEX;

public record ContactTo(
        @Pattern(regexp = PHONE_NUMBER_REGEX, message = PHONE_NUMBER_PATTERN_MESSAGE)
        String oldPhoneNumber,
        @Pattern(regexp = PHONE_NUMBER_REGEX, message = PHONE_NUMBER_PATTERN_MESSAGE)
        String newPhoneNumber,
        @Email
        String oldEmail,
        @Email
        String newEmail
) {
}