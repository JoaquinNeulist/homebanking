package com.mindhubbrothers.homebanking.repositoriesTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.mindhubbrothers.homebanking.models.Loans;
import com.mindhubbrothers.homebanking.repositories.LoansRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoanRepositoryTest {

//    @Autowired
//    private LoansRepository loanRepository;
//
//    @Test
//    public void findAllLoanTest() {
//        loanRepository.findAll();
//        assertThat(loanRepository.findAll(), is(not(empty())));
//    }
//
//    @Test
//    public void findLoanByIdAndGetNameTest(){
//        Optional<Loans> loan = loanRepository.findById(3L);
//        String nameLoan = loan.get().getName();
//        assertThat(nameLoan, is("Mortgage Loan"));
//    }

}
