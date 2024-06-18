package com.mindhubbrothers.homebanking.repositoriesTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.mindhubbrothers.homebanking.repositories.CardsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CardsRepositoryTest {

//    @Autowired
//    private CardsRepository cardsRepository;
//
//    @Test
//    public void findAllCardsTest() {
//        cardsRepository.findAll();
//        assertThat(cardsRepository.findAll(), is(not(empty())));
//    }

//    @Test
//    public void findByCardNumber() {
//        cardsRepository.existsByNumber("7118-9043-6079-5463");
//        assertThat(cardsRepository.existsByNumber("7118-9043-6079-5463"), is(true));
//    }
}
