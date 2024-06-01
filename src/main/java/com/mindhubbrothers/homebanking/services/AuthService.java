package com.mindhubbrothers.homebanking.services;

import com.mindhubbrothers.homebanking.dto.LoginDTO;
import com.mindhubbrothers.homebanking.dto.RegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface AuthService {
    ResponseEntity<?> login(LoginDTO loginDTO);
    ResponseEntity<?> register(RegisterDto registerDto);
    ResponseEntity<?> getCurrentClient(Authentication authentication);
}
