package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.auth.AuthenticationRequest;
import com.platformtrasnport.platformtransport.auth.AuthenticationResponse;
import com.platformtrasnport.platformtransport.auth.RegisterRequest;
import com.platformtrasnport.platformtransport.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register/registerParticulier")
    public ResponseEntity<AuthenticationResponse> registerParticulier(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.registerParticulier(request));
    }

    @PostMapping("/register/registerAdmin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }

    @PostMapping("/register/registerEmploye")
    public ResponseEntity<AuthenticationResponse> registerTechnicien(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.registerEmploye(request));
    }
    @PostMapping("/register/registerEmployeur")
    public ResponseEntity<AuthenticationResponse> registerEmployeur(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.registerEmployeur(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));

    }
}