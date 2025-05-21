package org.elis.controller;

import org.elis.dto.AuthResponseDTO;
import org.elis.dto.LoginRequestDTO;
import org.elis.dto.RichiestaRegistrazioneDTO;
import org.elis.dto.UtenteDTO;
import org.elis.facade.AuthFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController 
{
    private final AuthFacade authFacade;

    public AuthController(AuthFacade authFacade) 
    {
        this.authFacade = authFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<UtenteDTO> register(@Valid @RequestBody RichiestaRegistrazioneDTO registrazioneDTO) 
    {
        UtenteDTO utenteDTO = authFacade.register(registrazioneDTO);
        return new ResponseEntity<>(utenteDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginDTO) 
    {
        AuthResponseDTO authResponse = authFacade.login(loginDTO);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}