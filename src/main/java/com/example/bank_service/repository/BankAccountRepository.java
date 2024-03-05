package com.example.bank_service.repository;

import com.example.bank_service.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
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


    @Modifying
    @Query("""
                    UPDATE BankAccount ba
            SET ba.currentBalance = CASE
                WHEN ba.userId = :fromUserId AND ba.currentBalance >= :amount THEN ba.currentBalance - :amount
                WHEN ba.userId =:toUserId THEN ba.currentBalance + :amount
                ELSE ba.currentBalance
            END
            """)
    int transfer(@Param("fromUserId")String fromUserId, @Param("toUserId")String toUserId, @Param("amount")double amount);
}