package com.example.bank_service.service;

import com.example.bank_service.model.User;
import com.example.bank_service.repository.UserRepository;
import com.example.bank_service.web.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User register(User user) {
        return userRepository.prepareAndSave(user);
    }

    public Page<User> getFiltered(String birthDate, String fullName, Pageable page) {
        if (birthDate != null) {
            return userRepository.findAllWithAccountsByBirthDate(page, LocalDate.parse(birthDate));
        }
        if (fullName != null) {
            return userRepository.findAllWithContactsAndAccountsByFullName(page, fullName);
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