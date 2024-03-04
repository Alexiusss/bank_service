package com.example.bank_service;

import com.example.bank_service.model.BankAccount;
import com.example.bank_service.model.User;
import com.example.bank_service.to.UserTo;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class UserUtil {

    public static final String PHONE_NUMBER_REGEX = "([+]*[0-9]{1,4}\\s?[(]*\\d[0-9]{2,4}[)]*\\s?\\d{3}[-]*\\d{2}[-]*\\d{2})";
    public static final String PHONE_NUMBER_PATTERN_MESSAGE = "Please fill the phone number in format +1 (234) 567-89-10";
    public static User createNewFromTo(UserTo userTo) {
        BankAccount bankAccount = new BankAccount(userTo.startDeposit(), userTo.startDeposit());
        User user = new User(null, userTo.username(), userTo.password(), userTo.fullName(), Set.of(userTo.phoneNumber()), Set.of(userTo.email()), userTo.birthDate());
        bankAccount.setUser(user);
        user.setBankAccount(bankAccount);
        return user;
    }
}