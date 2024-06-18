package com.mindhubbrothers.homebanking.repositoriesTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.mindhubbrothers.homebanking.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionRepositoryTest {

//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    @Test
//    public void findAllTransactionTest() {
//        transactionRepository.findAll();
//        assertThat(transactionRepository.findAll(), is(not(empty())));
//    }
//
//    @Test
//    public void findTransactionByIdTest() {
//        transactionRepository.findById(1L);
//        assertThat(transactionRepository.findById(1L), is(notNullValue()));
//    }
}
