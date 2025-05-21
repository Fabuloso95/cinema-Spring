package org.elis.facade.impl;

import org.elis.dto.AuthResponseDTO;
import org.elis.dto.LoginRequestDTO;
import org.elis.dto.RichiestaRegistrazioneDTO;
import org.elis.dto.UtenteDTO;
import org.elis.facade.AuthFacade;
import org.elis.service.AuthService;
import org.springframework.stereotype.*;

@Service
public class AuthFacadeImpl implements AuthFacade 
{
    private final AuthService authService;

    public AuthFacadeImpl(AuthService authService) 
    {
        this.authService = authService;
    }

    @Override
    public UtenteDTO register(RichiestaRegistrazioneDTO registrazioneDTO) 
    {
        return authService.registraCliente(registrazioneDTO);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginDTO) 
    {
        return authService.login(loginDTO);
    }
}