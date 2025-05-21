package org.elis.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.elis.dto.AttoreDTO;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.elis.model.Attore;
import org.elis.repository.AttoreRepository;
import org.elis.service.AttoreService;
import org.springframework.stereotype.Service;

@Service
public class AttoreServiceImpl implements AttoreService 
{
    private final AttoreRepository attoreRepository;

    public AttoreServiceImpl(AttoreRepository attoreRepository) 
    {
        this.attoreRepository = attoreRepository;
    }

    private AttoreDTO toDTO(Attore attore) 
    {
        return new AttoreDTO(attore.getId(), attore.getNome(), attore.getCognome());
    }

    private Attore toEntity(AttoreDTO dto) 
    {
        Attore attore = new Attore();
        attore.setId(dto.getId());
        attore.setNome(dto.getNome());
        attore.setCognome(dto.getCognome());
        return attore;
    }

    @Override
    public List<AttoreDTO> getAllAttori()
    {
        return attoreRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public AttoreDTO getAttoreById(Long id) 
    {
        Attore attore = attoreRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Attore non trovato con id: " + id));
        return toDTO(attore);
    }

    @Override
    public AttoreDTO createAttore(AttoreDTO attoreDTO)
    {
        Attore attore = toEntity(attoreDTO);
        Attore saved = attoreRepository.save(attore);
        return toDTO(saved);
    }

    @Override
    public AttoreDTO updateAttore(Long id, AttoreDTO attoreDTO) 
    {
        Attore attore = attoreRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Attore non trovato con id: " + id));
        attore.setNome(attoreDTO.getNome());
        attore.setCognome(attoreDTO.getCognome());
        Attore updated = attoreRepository.save(attore);
        return toDTO(updated);
    }

    @Override
    public void deleteAttore(Long id) 
    {
        attoreRepository.deleteById(id);
    }

    @Override
    public List<AttoreDTO> getAttoriByNome(String nome) 
    {
        return attoreRepository.findByNomeContainingIgnoreCase(nome)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<AttoreDTO> getAttoriByCognome(String cognome) 
    {
        return attoreRepository.findByCognomeContainingIgnoreCase(cognome)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }
}