package com.mindhubbrothers.homebanking.services.implement;

import com.mindhubbrothers.homebanking.dto.LoginDTO;
import com.mindhubbrothers.homebanking.dto.RegisterDto;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.services.AccountService;
import com.mindhubbrothers.homebanking.services.ClientService;
import com.mindhubbrothers.homebanking.services.AuthService;
import com.mindhubbrothers.homebanking.services.servicesSecurity.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
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
        if (clientService.existsByEmail(registerDto.email())){
            return new ResponseEntity<>("Email is already in use", HttpStatus.FORBIDDEN);
        }
        Client client = new Client(registerDto.firstName(), registerDto.lastName(), registerDto.email(), passwordEncoder.encode(registerDto.password()));
        clientService.saveClient(client);
        accountService.createAccount(client);
        return new ResponseEntity<>("Client and account created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> login(LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.email());
            final String jwt = jwtUtilService.generateToken(userDetails);
            return ResponseEntity.ok(jwt);
        }catch (Exception e){
            return new ResponseEntity<>("Email or password invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getCurrentClient(Authentication authentication) {
        Client client = clientService.findByEmail(authentication.getName());
        return ResponseEntity.ok(clientService.getClientDTO(client));
    }
}
