package org.elis.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.elis.dto.*;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.elis.model.Biglietto;
import org.elis.model.Utente;
import org.elis.model.Ruolo;
import org.elis.repository.BigliettoRepository;
import org.elis.repository.UtenteRepository;
import org.elis.service.UtenteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UtenteServiceImpl implements UtenteService
{
    private final UtenteRepository utenteRepository;
    private final BigliettoRepository bigliettoRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UtenteServiceImpl(UtenteRepository utenteRepository, BigliettoRepository bigliettoRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder)
    {
        this.utenteRepository = utenteRepository;
        this.bigliettoRepository = bigliettoRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UtenteDTO> getAllUtenti()
    {
        List<Utente> utenti = utenteRepository.findAll();
        return utenti.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UtenteDTO getUtenteById(Long id)
    {
        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Utente non trovato con id: " + id));
        return mapEntityToDTO(utente);
    }

    @Override
    @Transactional
    public UtenteDTO createUtente(UtenteDTO utenteDTO)
    {
        Utente utente = mapDTOToEntity(utenteDTO);

        if (utenteDTO.getPassword() != null && !utenteDTO.getPassword().isEmpty()) 
        {
            utente.setPassword(passwordEncoder.encode(utenteDTO.getPassword()));
        }
        else
        {
             throw new IllegalArgumentException("Password non fornita per la creazione utente.");
        }


        Utente createdUtente = utenteRepository.save(utente);

        return mapEntityToDTO(createdUtente);
    }

    @Override
    @Transactional
    public UtenteDTO updateUtente(Long id, UtenteDTO utenteDTO)
    {
        Utente existingUtente = utenteRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Utente non trovato con id: " + id));

        updateEntityFromDTO(existingUtente, utenteDTO);


        Utente updatedUtente = utenteRepository.save(existingUtente);
        return mapEntityToDTO(updatedUtente);
    }

    @Override
    @Transactional
    public void deleteUtente(Long id)
    {
        utenteRepository.deleteById(id);
    }

    @Override
    public Optional<UtenteDTO> findByUsername(String username)
    {
        Optional<Utente> utente = utenteRepository.findByUsername(username);
        return utente.map(this::mapEntityToDTO);
    }

    @Override
    public List<BigliettoDTO> getBigliettiAcquistati(Long idUtente)
    {
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new RisorsaNonTrovataException("Utente non trovato con id: " + idUtente));

        List<Biglietto> biglietti = bigliettoRepository.findByCliente(utente);

        return biglietti.stream()
                .map(this::mapBigliettoEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UtenteDTO> searchByNomeECognome(String nome, String cognome)
    {
        List<Utente> utenti = utenteRepository.findByNomeAndCognome(nome, cognome);
        return utenti.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UtenteDTO> findByDataNascitaBefore(LocalDate data)
    {
        List<Utente> utenti = utenteRepository.findByDataNascitaBefore(data);
        return utenti.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UtenteDTO> findByDataNascitaAfter(LocalDate data)
    {
        List<Utente> utenti = utenteRepository.findByDataNascitaAfter(data);
        return utenti.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UtenteDTO> findByDataNascitaBetween(LocalDate from, LocalDate to)
    {
        List<Utente> utenti = utenteRepository.findByDataNascitaBetween(from, to);
        return utenti.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UtenteDTO> searchUtenti(String q)
    {
        List<Utente> utenti = utenteRepository.searchUtenti(q);
        return utenti.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    private UtenteDTO mapEntityToDTO(Utente utente)
    {
        if (utente == null) return null;
        UtenteDTO dto = modelMapper.map(utente, UtenteDTO.class);
        if (utente.getRuolo() != null) {
            dto.setRuolo(utente.getRuolo().name());
        }
        dto.setPassword(null);
        return dto;
    }

     private Utente mapDTOToEntity(UtenteDTO utenteDTO)
     {
         if (utenteDTO == null) return null;
         Utente entity = modelMapper.map(utenteDTO, Utente.class);
         if (utenteDTO.getRuolo() != null)
         {
             entity.setRuolo(Ruolo.valueOf(utenteDTO.getRuolo()));
         }
         return entity;
     }

     private void updateEntityFromDTO(Utente existingUtente, UtenteDTO utenteDTO)
     {
         if (existingUtente == null || utenteDTO == null) return;
         if (utenteDTO.getNome() != null) existingUtente.setNome(utenteDTO.getNome());
         if (utenteDTO.getCognome() != null) existingUtente.setCognome(utenteDTO.getCognome());
         if (utenteDTO.getUsername() != null) existingUtente.setUsername(utenteDTO.getUsername());
         if (utenteDTO.getRuolo() != null) existingUtente.setRuolo(Ruolo.valueOf(utenteDTO.getRuolo()));
         if (utenteDTO.getDataNascita() != null) existingUtente.setDataNascita(utenteDTO.getDataNascita());
     }

     private BigliettoDTO mapBigliettoEntityToDTO(Biglietto biglietto)
     {
         if (biglietto == null) return null;
         return modelMapper.map(biglietto, BigliettoDTO.class);
     }
}