package org.elis.service;

import org.elis.dto.RegistaDTO;
import java.util.List;
import java.util.Optional;

public interface RegistaService 
{
    List<RegistaDTO> getAllRegisti();
    RegistaDTO getRegistaById(Long id);
    RegistaDTO createRegista(RegistaDTO registaDTO);
    RegistaDTO updateRegista(Long id, RegistaDTO registaDTO);
    void deleteRegista(Long id);
    Optional<RegistaDTO> findByNomeAndCognome(String nome, String cognome);
}