package com.example.bank_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"user"})
@Table(name = "accounts")
public class BankAccount {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "start_deposit")
    private double startDeposit;

    @Column(name = "current_balance")
    private double currentBalance;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private User user;

    public BankAccount(double startDeposit) {
        this.startDeposit = startDeposit;
    }
}