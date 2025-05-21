package org.elis.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import org.elis.dto.*;
import org.elis.model.*;
import org.elis.repository.ImpiegatoRepository;
import org.elis.service.*;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class ImpiegatoServiceImpl implements ImpiegatoService
{
    private final ImpiegatoRepository impiegatoRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final FilmService filmService;
    private final SpettacoloService spettacoloService;
    private final ProdottoMenuService prodottoMenuService;

    public ImpiegatoServiceImpl(ImpiegatoRepository impiegatoRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, FilmService filmService, SpettacoloService spettacoloService, ProdottoMenuService prodottoMenuService)
    {
        this.impiegatoRepository = impiegatoRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.filmService = filmService;
        this.spettacoloService = spettacoloService;
        this.prodottoMenuService = prodottoMenuService;
    }

    @Override
    public List<ImpiegatoDTO> getAllImpiegati()
    {
        List<Impiegato> impiegati = impiegatoRepository.findAll();
        return impiegati.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImpiegatoDTO getImpiegatoById(Long id)
    {
        Impiegato impiegato = impiegatoRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Impiegato non trovato con id: " + id));
        return mapEntityToDTO(impiegato);
    }

    @Override
    @Transactional
    public ImpiegatoDTO createImpiegato(ImpiegatoDTO impiegatoDTO)
    {
        Impiegato impiegato = mapDTOToEntity(impiegatoDTO);

        if (impiegatoDTO.getPassword() != null && !impiegatoDTO.getPassword().isEmpty())
        {
            impiegato.setPassword(passwordEncoder.encode(impiegatoDTO.getPassword()));
        }
        else
        {
             throw new IllegalArgumentException("Password non fornita per la creazione impiegato.");
        }

        if (impiegato.getRuolo() == null)
        {
            impiegato.setRuolo(Ruolo.IMPIEGATO);
        }
        Impiegato nuovoImpiegato = impiegatoRepository.save(impiegato);

        return mapEntityToDTO(nuovoImpiegato);
    }

    @Override
    @Transactional
    public ImpiegatoDTO updateImpiegato(Long id, ImpiegatoDTO impiegatoDTO)
    {
        Impiegato existingImpiegato = impiegatoRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Impiegato non trovato con id: " + id));

        updateEntityFromDTO(existingImpiegato, impiegatoDTO);

        Impiegato impiegatoAggiornato = impiegatoRepository.save(existingImpiegato);
        return mapEntityToDTO(impiegatoAggiornato);
    }

    @Override
    @Transactional
    public void deleteImpiegato(Long id)
    {
        impiegatoRepository.deleteById(id);
    }

    @Override
    public Optional<ImpiegatoDTO> findByUsername(String username)
    {
        Optional<Impiegato> impiegato = impiegatoRepository.findByUsername(username);
        return impiegato.map(this::mapEntityToDTO);
    }

    @Override
    public FilmDTO createFilm(FilmDTO filmDTO)
    {
        return filmService.createFilm(filmDTO);
    }

    @Override
    public FilmDTO updateFilm(Long id, FilmDTO filmDTO) 
    {
        return filmService.updateFilm(id, filmDTO);
    }

    @Override
    public void deleteFilm(Long id) 
    {
        filmService.deleteFilm(id);
    }

    @Override
    public SpettacoloDTO createSpettacolo(SpettacoloCreationDTO spettacoloDTO)
    {
        return spettacoloService.createSpettacolo(spettacoloDTO);
    }

    @Override
    public SpettacoloDTO updateSpettacolo(Long id, SpettacoloUpdateDTO spettacoloDTO) 
    {
        return spettacoloService.updateSpettacolo(id, spettacoloDTO);
    }

    @Override
    public void deleteSpettacolo(Long id) 
    {
        spettacoloService.deleteSpettacolo(id);
    }

    @Override
    public ProdottoMenuDTO createProdottoMenu(ProdottoMenuDTO prodottoMenuDTO)
    {
        return prodottoMenuService.createMenu(prodottoMenuDTO);
    }

    @Override
    public ProdottoMenuDTO updateProdottoMenu(Long id, ProdottoMenuDTO prodottoMenuDTO) 
    {
        return prodottoMenuService.updateMenu(id, prodottoMenuDTO);
    }

    @Override
    public void deleteProdottoMenu(Long id) 
    {
        prodottoMenuService.deleteMenu(id);
    }

    @Override
    public SpettacoloDTO updateSpettacoloPrezzoBase(Long idSpettacolo, double nuovoPrezzoBase)
    {
        SpettacoloDTO existingSpettacoloDTO = spettacoloService.getSpettacoloById(idSpettacolo);
        SpettacoloUpdateDTO updateDTO = new SpettacoloUpdateDTO();
        updateDTO.setDataOra(existingSpettacoloDTO.getDataOra());
        updateDTO.setFilmId(existingSpettacoloDTO.getFilm().getId());
        updateDTO.setSalaId(existingSpettacoloDTO.getSala().getId());
        updateDTO.setPrezzoBaseBiglietto(nuovoPrezzoBase);
        return spettacoloService.updateSpettacolo(idSpettacolo, updateDTO);
    }

    private ImpiegatoDTO mapEntityToDTO(Impiegato impiegato)
    {
        if (impiegato == null) return null;
        ImpiegatoDTO dto = modelMapper.map(impiegato, ImpiegatoDTO.class);
        if (impiegato.getRuolo() != null)
        {
            dto.setRuolo(impiegato.getRuolo().name());
        }
        dto.setPassword(null);
        return dto;
    }

    private Impiegato mapDTOToEntity(ImpiegatoDTO impiegatoDTO)
    {
        if (impiegatoDTO == null) return null;
        Impiegato entity = modelMapper.map(impiegatoDTO, Impiegato.class);
        if (impiegatoDTO.getRuolo() != null)
        {
            entity.setRuolo(Ruolo.valueOf(impiegatoDTO.getRuolo()));
        }
        return entity;
    }

    private void updateEntityFromDTO(Impiegato existingImpiegato, ImpiegatoDTO impiegatoDTO)
    {
         if (existingImpiegato == null || impiegatoDTO == null) return;
         if (impiegatoDTO.getNome() != null) existingImpiegato.setNome(impiegatoDTO.getNome());
         if (impiegatoDTO.getCognome() != null) existingImpiegato.setCognome(impiegatoDTO.getCognome());
         if (impiegatoDTO.getUsername() != null) existingImpiegato.setUsername(impiegatoDTO.getUsername());
         if (impiegatoDTO.getRuolo() != null) existingImpiegato.setRuolo(Ruolo.valueOf(impiegatoDTO.getRuolo()));
         if (impiegatoDTO.getDataNascita() != null) existingImpiegato.setDataNascita(impiegatoDTO.getDataNascita());
    }
}