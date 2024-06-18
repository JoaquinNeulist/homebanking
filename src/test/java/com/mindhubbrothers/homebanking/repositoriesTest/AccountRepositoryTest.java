package com.mindhubbrothers.homebanking.repositoriesTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.mindhubbrothers.homebanking.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Test
//    public void findAllAccountTest() {
//        accountRepository.findById(4L);
//        assertThat(accountRepository.findById(4L), is(notNullValue()));
//    }
//
//    @Test
//    public void find(){
//        accountRepository.findByNumber("VIN001");
//        assertThat(accountRepository.findByNumber("VIN001"), is(notNullValue()));
//    }
}
