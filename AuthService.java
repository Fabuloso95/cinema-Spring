package org.elis.service;

import org.elis.dto.AuthResponseDTO;
import org.elis.dto.LoginRequestDTO;
import org.elis.dto.RichiestaRegistrazioneDTO;
import org.elis.dto.UtenteDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService
{
    UtenteDTO registraCliente(RichiestaRegistrazioneDTO registrazioneDTO);
    AuthResponseDTO login(LoginRequestDTO loginDTO);
}