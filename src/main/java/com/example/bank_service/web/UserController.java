package com.example.bank_service.web;

import com.example.bank_service.model.User;
import com.example.bank_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = UserController.REST_URL, produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {
    static final String REST_URL = "/api/v1/users";

    UserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> getAllFiltered(
            @RequestParam(value = "birthDate", required = false) String birthDate,
            @RequestParam(value = "fullName", required = false) String fullName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(userService.getFiltered(birthDate, fullName, PageRequest.of(page, size)));
    }

    @GetMapping("/filter")
    public ResponseEntity<User> getByFilter(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber
    ) {
        return ResponseEntity.ok(userService.getByFilter(email, phoneNumber));
    }
}