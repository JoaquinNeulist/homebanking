package com.mindhubbrothers.homebanking.utilsTest;
import com.mindhubbrothers.homebanking.repositories.AccountRepository;
import com.mindhubbrothers.homebanking.repositories.CardsRepository;
import com.mindhubbrothers.homebanking.utils.AccountNumberGenerator;
import com.mindhubbrothers.homebanking.utils.CVVGenerator;
import com.mindhubbrothers.homebanking.utils.CardNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UtilsTest {

//    @Mock
//    private CardsRepository cardsRepository;
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @InjectMocks
//    private CardNumberGenerator cardNumberGenerator;
//
//    @InjectMocks
//    private AccountNumberGenerator accountNumberGenerator;
//
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.openMocks(this); // Inicializa @Mock y @InjectMocks
//    }
//
//    @Test
//    public void testGenerateCVVMatchPattern(){
//        String cvv = String.valueOf(CVVGenerator.generate());
//        assertThat(cvv, matchesPattern("\\d{3}"));
//    }
//
//    @Test
//    public void testGenerateCVV(){
//        String cvv = String.valueOf(CVVGenerator.generate());
//        assertThat(cvv, is(notNullValue()));
//    }
//
//    @Test
//    public void testGenerateCardNumberMatchPattern(){
//        String cardNumber = CardNumberGenerator.generate();
//        assertThat(cardNumber, matchesPattern("\\d{4}-\\d{4}-\\d{4}-\\d{4}"));
//    }
//
//    @Test
//    public void testGenerateCardNumber(){
//        String cardNumber = CardNumberGenerator.generate();
//        assertThat(cardNumber, is(notNullValue()));
//    }
//
//    @Test
//    public void testGenerateAccountNumberMatchPattern(){
//        String accountNumber = AccountNumberGenerator.generate();
//        assertThat(accountNumber, matchesPattern("VIN-\\d{4}"));
//    }
//
//    @Test
//    public void testGenerateAccountNumber(){
//        String accountNumber = AccountNumberGenerator.generate();
//        assertThat(accountNumber, is(notNullValue()));
//    }
}
