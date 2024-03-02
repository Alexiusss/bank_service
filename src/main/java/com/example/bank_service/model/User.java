package com.example.bank_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"password", "bankAccount", "phoneNumbers", "emails"})
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @Version
    @Column(name = "version")
    private int version;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Instant modifiedAt;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    @ElementCollection
    @CollectionTable(name = "phone_numbers", joinColumns = {@JoinColumn(name = "user_id")})
    private Set<String> phoneNumbers;

    @Column(name = "email")
    @ElementCollection
    @CollectionTable(name = "emails", joinColumns = {@JoinColumn(name = "user_id")})
    private Set<String> emails;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @PrimaryKeyJoinColumn
    private BankAccount bankAccount;

    public User(String id, String username, String password, String fullName, Set<String> phoneNumbers, Set<String> emails, LocalDate birthDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumbers = phoneNumbers;
        this.emails = emails;
        this.birthDate = birthDate;
    }
}