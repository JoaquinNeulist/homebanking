package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.dto.ClientDTO;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/Clients")
public class ClientControllers {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/hello")
    public String getClients(){return "Hello Clients!";}

    @GetMapping("/")
   public ResponseEntity<?> getAllClients(){
        List<Client> clientList = clientRepository.findAll();
        List<ClientDTO> clientDTOList = clientList.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
        if (!clientDTOList.isEmpty()){
            return new ResponseEntity<>(clientDTOList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Clients not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getClient (@PathVariable long id){
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            ClientDTO clientDTO = new ClientDTO(client);
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        }
    }
}

