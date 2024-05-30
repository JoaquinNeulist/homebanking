package com.mindhubbrothers.homebanking.services;

import com.mindhubbrothers.homebanking.dto.ClientDTO;
import com.mindhubbrothers.homebanking.models.Client;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getListClientsDTO();

    ClientDTO getClientDTO(Client client);

    Client findById(Long id);

    Client findByEmail(String email);

    Boolean existsByEmail(String email);

    void saveClient(Client client);
}
