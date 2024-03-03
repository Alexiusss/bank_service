package com.example.bank_service.repository;

import com.example.bank_service.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("""
            SELECT u FROM User u 
            """)
    @EntityGraph(attributePaths = {"bankAccount", "phoneNumbers", "emails"})
    Page<User> findAll(Pageable pageable);

    @Query("""
            SELECT u FROM User u
            WHERE u.birthDate > :birth_date             
            """)
    @EntityGraph(attributePaths = {"bankAccount", "phoneNumbers", "emails"})
    Page<User> findAllWithAccountsByBirthDate(Pageable pageable, @Param("birth_date") LocalDate birthDate);

    @Query(value = """
            SELECT u FROM User u
            WHERE :phone_number MEMBER OF u.phoneNumbers 
                        """)
    @EntityGraph(attributePaths = {"bankAccount", "phoneNumbers", "emails"})
    Optional<User> findUserByPhoneNumber(@Param("phone_number") String phoneNumber);

    @Query("""
            SELECT u FROM User u
            WHERE u.fullName LIKE concat('%', :full_name,'%')          
            """)
    @EntityGraph(attributePaths = {"bankAccount", "phoneNumbers", "emails"})
    Page<User> findAllWithContactsAndAccountsByFullName(Pageable pageable, @Param("full_name") String fullName);


    @Query(value = """
            SELECT u FROM User u
            WHERE :email MEMBER OF u.emails 
                        """)
    @EntityGraph(attributePaths = {"bankAccount", "phoneNumbers", "emails"})
    Optional<User> findUserByEmail(@Param("email") String email);

    Optional<User> findByUsername(String username);
}