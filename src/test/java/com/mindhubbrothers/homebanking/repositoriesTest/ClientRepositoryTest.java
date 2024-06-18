package com.mindhubbrothers.homebanking.repositoriesTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientRepositoryTest {

//    @Autowired
//    private ClientRepository clientRepository;
//
//    @Test
//    public void findClientByEmail(){
//        clientRepository.findByEmail("melba@mindhub.com");
//        assertThat(clientRepository.findByEmail("melba@mindhub.com"), is(notNullValue()));
//    }
//
//    @Test
//    public void findClientLastName(){
//        Optional<Client> client = clientRepository.findById(2L);
//        String lastName = client.get().getLastName();
//        assertThat(lastName, is("Morel"));
//    }
}
