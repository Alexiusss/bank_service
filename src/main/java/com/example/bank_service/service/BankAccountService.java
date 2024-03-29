package com.example.bank_service.service;

import com.example.bank_service.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    @Value("${interest.rate}")
    private double interestRate;
    @Value("${max.rate}")
    private double maxRate;

    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    @Transactional
    public void chargeInterests() {
        repository.chargeInterests(interestRate, maxRate);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public boolean transferMoney(String fromUserId, String toUserId, double amount) {
        boolean status = true;

        long fromBalance = repository.getBalance(fromUserId);

        if (fromBalance >= amount) {
            status &= repository.addBalance(
                    fromUserId, (-1) * amount
            ) > 0;

            status &= repository.addBalance(
                    toUserId, amount
            ) > 0;
        }

        return status;
    }

}