package com.example.bank_service.repository;

import com.example.bank_service.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    @Modifying
    @Query("""
                    UPDATE BankAccount ba
            SET ba.currentBalance = CASE
                WHEN ba.currentBalance = 0 AND ba.currentBalance >= ba.startDeposit * :maxRate THEN ba.currentBalance
                ELSE LEAST(ba.currentBalance * :interestRate, ba.startDeposit * :maxRate)
            END
            """)
    void chargeInterests(@Param("interestRate") double interestRate, @Param("maxRate") double maxRate);

    @Query(value = """
        SELECT current_balance
        FROM accounts
        WHERE user_id = :user_id
        """,
            nativeQuery = true)
    long getBalance(@Param("user_id") String userId);

    @Query(value = """
        UPDATE accounts
        SET current_balance = current_balance + :cents
        WHERE user_id = :user_id
        """,
            nativeQuery = true)
    @Modifying
    @Transactional
    int addBalance(@Param("user_id") String userId, @Param("cents") double cents);
}