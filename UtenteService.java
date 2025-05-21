package org.elis.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.elis.dto.UtenteDTO;
import org.elis.dto.BigliettoDTO;

public interface UtenteService
{
    List<UtenteDTO> getAllUtenti();
    UtenteDTO getUtenteById(Long id);
    UtenteDTO createUtente(UtenteDTO utenteDTO);
    UtenteDTO updateUtente(Long id, UtenteDTO utenteDTO);
    void deleteUtente(Long id);
    Optional<UtenteDTO> findByUsername(String username);
    List<BigliettoDTO> getBigliettiAcquistati(Long idUtente);
    List<UtenteDTO> searchByNomeECognome(String nome, String cognome);
    List<UtenteDTO> findByDataNascitaBefore(LocalDate data);
    List<UtenteDTO> findByDataNascitaAfter(LocalDate data);
    List<UtenteDTO> findByDataNascitaBetween(LocalDate from, LocalDate to);
    List<UtenteDTO> searchUtenti(String q);
}