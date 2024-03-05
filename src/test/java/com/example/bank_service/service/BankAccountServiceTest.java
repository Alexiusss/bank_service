package com.example.bank_service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@Transactional
@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles({ "test" })
@Sql(value = {"/test-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class BankAccountServiceTest {

    @MockBean
    private BankAccountService bankAccountService;

    @Test
    void transferMoney() {
        bankAccountService.transferMoney("user1ID", "user2ID", 10);
    }
}