package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.dto.ClientDTO;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientControllers {
    @Autowired
    private ClientService clientService;

    @GetMapping("/hello")
    public String getClients(){return "Hello Clients!";}

    @GetMapping("/")
   public ResponseEntity<?> getAllClients(){
        List<ClientDTO> clientDTOList = clientService.getListClientsDTO();
        if (!clientDTOList.isEmpty()){
            return new ResponseEntity<>(clientDTOList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Clients not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getClient (@PathVariable long id){
        Client client = clientService.findById(id);
        if (client == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            ClientDTO clientDTO = clientService.getClientDTO(client);
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        }
    }
}

