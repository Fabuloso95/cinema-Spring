package org.elis.facade.impl;

import org.elis.dto.UtenteDTO;
import org.elis.dto.BigliettoDTO;
import org.elis.facade.UtenteFacade;
import org.elis.service.UtenteService;
import org.springframework.stereotype.*;
import java.time.LocalDate;
import java.util.*;

@Service
public class UtenteFacadeImpl implements UtenteFacade 
{
    private final UtenteService utenteService;

    public UtenteFacadeImpl(UtenteService utenteService) 
    {
        this.utenteService = utenteService;
    }

    @Override
    public List<UtenteDTO> getAllUtenti()
    {
        return utenteService.getAllUtenti();
    }

    @Override
    public UtenteDTO getUtenteById(Long id) 
    {
        return utenteService.getUtenteById(id);
    }

    @Override
    public UtenteDTO createUtente(UtenteDTO utenteDTO) 
    {
        return utenteService.createUtente(utenteDTO);
    }

    @Override
    public UtenteDTO updateUtente(Long id, UtenteDTO utenteDTO) 
    {
        return utenteService.updateUtente(id, utenteDTO);
    }

    @Override
    public void deleteUtente(Long id) 
    {
        utenteService.deleteUtente(id);
    }

    @Override
    public Optional<UtenteDTO> findByUsername(String username) 
    {
        return utenteService.findByUsername(username);
    }

    @Override
    public List<BigliettoDTO> getBigliettiAcquistati(Long idUtente) 
    {
        return utenteService.getBigliettiAcquistati(idUtente);
    }

    @Override
    public List<UtenteDTO> searchByNomeECognome(String nome, String cognome) 
    {
        return utenteService.searchByNomeECognome(nome, cognome);
    }

    @Override
    public List<UtenteDTO> findByDataNascitaBefore(LocalDate data) 
    {
        return utenteService.findByDataNascitaBefore(data);
    }

    @Override
    public List<UtenteDTO> findByDataNascitaAfter(LocalDate data)
    {
        return utenteService.findByDataNascitaAfter(data);
    }

    @Override
    public List<UtenteDTO> findByDataNascitaBetween(LocalDate from, LocalDate to)
    {
        return utenteService.findByDataNascitaBetween(from, to);
    }

    @Override
    public List<UtenteDTO> searchUtenti(String q) 
    {
        return utenteService.searchUtenti(q);
    }
}