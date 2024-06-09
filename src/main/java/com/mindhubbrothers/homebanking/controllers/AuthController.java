package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.dto.LoginDTO;
import com.mindhubbrothers.homebanking.dto.RegisterDto;
import com.mindhubbrothers.homebanking.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/test")
    public ResponseEntity<?> test (Authentication authentication){
        String mail = authentication.getName();
        return ResponseEntity.ok("Hello " + mail);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDTO loginDTO){
        return authService.login(loginDTO);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto){
        return authService.register(registerDto);
    }

    @GetMapping("/current")
    public ResponseEntity<?> getClient(Authentication authentication){
        return authService.getCurrentClient(authentication);
    }
}
