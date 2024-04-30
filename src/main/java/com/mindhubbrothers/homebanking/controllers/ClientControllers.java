package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientControllers {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/hello")
    public String getClients(){return "Hello Clients!";}

    @GetMapping("/")
    public List<Client> getAllClients(){return clientRepository.findAll();}
}
