package org.elis.facade;

import org.elis.dto.AuthResponseDTO;
import org.elis.dto.LoginRequestDTO;
import org.elis.dto.RichiestaRegistrazioneDTO;
import org.elis.dto.UtenteDTO;

public interface AuthFacade 
{
    UtenteDTO register(RichiestaRegistrazioneDTO registrazioneDTO);
    AuthResponseDTO login(LoginRequestDTO loginDTO);
}