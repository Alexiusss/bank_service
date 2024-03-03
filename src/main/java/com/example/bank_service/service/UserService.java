package com.example.bank_service.service;

import com.example.bank_service.model.User;
import com.example.bank_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public Page<User> getFiltered(String birthDate, String fullName, Pageable page) {
        if (birthDate != null) {
            userRepository.findAllWithAccountsByBirthDate(page, LocalDate.parse(birthDate));
        }
        if (fullName != null) {
            userRepository.findAllWithContactsAndAccountsByFullName(page, fullName);
        }
        return userRepository.findAll(page);
    }

    public User getByFilter(String email, String phoneNumber) {
        if (email != null) {
            return userRepository.findUserByEmail(email).orElseThrow();
        }
        if (phoneNumber != null) {
            return userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow();
        }
        throw new IllegalArgumentException("Filter must not be empty");
    }
}