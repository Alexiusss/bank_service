package com.example.bank_service.web;

import com.example.bank_service.service.BankAccountService;
import com.example.bank_service.service.UserService;
import com.example.bank_service.to.ContactTo;
import com.example.bank_service.to.TransferTo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = ProfileController.REST_URL, produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ProfileController {
    static final String REST_URL = "/api/v1/profile";

    private final UserService userService;
    private final BankAccountService bankAccountService;

    @Operation(summary = "Add email and/or phone number for the authenticated user")
    @SecurityRequirement(name = "basicAuth")
    @PostMapping(value = "/contacts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addContacts(@AuthenticationPrincipal AuthUser authUser,
                            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                            @RequestParam(value = "email", required = false) String email
    ) {
        userService.addContacts(phoneNumber, email, authUser);
    }

    @Operation(summary = "Edit email and/or phone number for the authenticated user")
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping(value = "/contacts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editContacts(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody ContactTo contactTo
    ) {
        userService.editContacts(contactTo, authUser);
    }

    @Operation(summary = "Delete email and/or phone number for the authenticated user")
    @SecurityRequirement(name = "basicAuth")
    @DeleteMapping(value = "/contacts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContacts(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "email", required = false) String email
    ) {
        userService.deleteContacts(phoneNumber, email, authUser);
    }

    @Operation(summary = "Transfer money from an authenticated user to another user using their id")
    @SecurityRequirement(name = "basicAuth")
    @PostMapping(value = "/transfer")
    public void transferMoney(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody TransferTo transferTo) {
        bankAccountService.transferMoney(authUser.id(), transferTo.toUserId(), transferTo.amount());
    }
}