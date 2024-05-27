package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.dto.ClientDTO;
import com.mindhubbrothers.homebanking.dto.LoginDTO;
import com.mindhubbrothers.homebanking.dto.RegisterDto;
import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.AccountRepository;
import com.mindhubbrothers.homebanking.repositories.ClientRepository;
import com.mindhubbrothers.homebanking.servicesSecurity.JwtUtilService;
import com.mindhubbrothers.homebanking.utils.GenerateAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Random;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private GenerateAccount generateAccount;

    @GetMapping("/test")
    public ResponseEntity<?> test (Authentication authentication){
        String mail = authentication.getName();
        return ResponseEntity.ok("Hello " + mail);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDTO loginDTO){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.email());
            final String jwt = jwtUtilService.generateToken(userDetails);
            return ResponseEntity.ok(jwt);
        }catch (Exception e){
            return new ResponseEntity<>("Email or password invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto){
        if (registerDto.firstName().isBlank()){
            return new ResponseEntity<>("The first name field must not be empty", HttpStatus.FORBIDDEN);
        }
        if (registerDto.lastName().isBlank()){
            return new ResponseEntity<>("The last name field must not be empty", HttpStatus.FORBIDDEN);
        }
        if (registerDto.email().isBlank()){
            return new ResponseEntity<>("The email field must not be empty", HttpStatus.FORBIDDEN);
        }
        if (registerDto.password().isBlank()){
            return new ResponseEntity<>("The password field must not be empty", HttpStatus.FORBIDDEN);
        }
        if (clientRepository.findByEmail(registerDto.email()) != null){
            return new ResponseEntity<>("Email is already in use", HttpStatus.FORBIDDEN);
        }
        Client client = new Client(
                registerDto.firstName(), registerDto.lastName(), registerDto.email(), passwordEncoder.encode(registerDto.password()));
        clientRepository.save(client);
        generateAccount.createAccount(client);

        return new ResponseEntity<>("Client and account created succesfully", HttpStatus.CREATED);
    }

    @GetMapping("/current")
    public ResponseEntity<?> getClient(Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());
        return ResponseEntity.ok(new ClientDTO(client));
    }
}
