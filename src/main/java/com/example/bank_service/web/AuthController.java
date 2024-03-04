package com.example.bank_service.web;

import com.example.bank_service.model.User;
import com.example.bank_service.service.UserService;
import com.example.bank_service.to.UserTo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.example.bank_service.UserUtil.createNewFromTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = AuthController.REST_URL, produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthController {
    static final String REST_URL = "/api/v1/auth";

    UserService userService;

    @Operation(summary = "Registration of a new user account")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody UserTo userTo) {
        User created = userService.register(createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}