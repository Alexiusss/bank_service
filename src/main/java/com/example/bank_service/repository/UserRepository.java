package com.example.bank_service.repository;

import com.example.bank_service.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("""
            SELECT u FROM User u
            LEFT OUTER JOIN FETCH u.bankAccount
            WHERE u.birthDate > :birth_date             
            """)
    Page<User> findAllWithAccountsByBirthDate(Pageable pageable, @Param("birth_date") LocalDate birthDate);

    @Query(value = """
            SELECT * FROM users
            LEFT OUTER JOIN accounts ON users.id = accounts.user_id
            WHERE users.id = (
             SELECT phone_numbers.user_id FROM phone_numbers
             WHERE phone_numbers.phone_number = :phone_number
             )
             """,
            nativeQuery = true)
    Optional<User> findUserByPhoneNumber(@Param("phone_number") String phoneNumber);

    @Query("""
            SELECT u FROM User u
            LEFT OUTER JOIN FETCH u.bankAccount
            WHERE u.fullName LIKE concat('%', :full_name,'%')           
            """)
    Page<User> findAllWithAccountsByFullName(Pageable pageable, @Param("full_name") String fullName);


    @Query(value = """
            SELECT * FROM users
            LEFT OUTER JOIN accounts ON users.id = accounts.user_id
            WHERE users.id = (
             SELECT emails.user_id FROM emails
             WHERE emails.email = :email
             )
             """,
            nativeQuery = true)
    Optional<User> findUserByEmail(@Param("email") String email);
}