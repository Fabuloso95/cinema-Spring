package org.elis.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.elis.dto.SalaDTO;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.elis.model.Sala;
import org.elis.repository.SalaRepository;
import org.elis.service.SalaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalaServiceImpl implements SalaService
{
    private final SalaRepository salaRepository;

    public SalaServiceImpl(SalaRepository salaRepository)
    {
        this.salaRepository = salaRepository;
    }

    @Override
    public List<SalaDTO> getAllSale()
    {
        List<Sala> sale = salaRepository.findAll();
        return mapSalaListToDTOList(sale);
    }

    @Override
    public SalaDTO getSalaById(Long id)
    {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Sala non trovata con id: " + id));
        return mapSalaToDTO(sala);
    }

    @Override
    @Transactional
    public SalaDTO createSala(SalaDTO salaDTO)
    {
        Sala sala = mapSalaDTOToEntity(salaDTO);
        sala = salaRepository.save(sala);
        return mapSalaToDTO(sala);
    }

    @Override
    @Transactional
    public SalaDTO updateSala(Long id, SalaDTO salaDTO)
    {
        Sala existingSala = salaRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Sala non trovata con id: " + id));

        updateSalaFromDTO(existingSala, salaDTO);

        Sala updatedSala = salaRepository.save(existingSala);
        return mapSalaToDTO(updatedSala);
    }

    @Override
    @Transactional
    public void deleteSala(Long id)
    {
        salaRepository.deleteById(id);
    }

    @Override
    public SalaDTO getSalaByNome(String nome)
    {
        Sala sala = salaRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new RisorsaNonTrovataException("Sala non trovata con nome: " + nome));
        return mapSalaToDTO(sala);
    }

    @Override
    public List<SalaDTO> getSaleByCapienza(int capienza)
    {
        List<Sala> sale = salaRepository.findByCapienzaGreaterThanEqual(capienza);
        return mapSalaListToDTOList(sale);
    }

    @Override
    public List<SalaDTO> getSaleUsufruibili()
    {
        List<Sala> sale = salaRepository.findByIsUsufruibile(true);
        return mapSalaListToDTOList(sale);
    }

    private SalaDTO mapSalaToDTO(Sala sala)
    {
        if (sala == null) return null;
        SalaDTO dto = new SalaDTO();
        dto.setId(sala.getId());
        dto.setNome(sala.getNome());
        dto.setCapienza(sala.getCapienza());
        dto.setUsufruibile(sala.isUsufruibile());
        return dto;
    }

    private List<SalaDTO> mapSalaListToDTOList(List<Sala> sale)
    {
        if (sale == null) return null;
        return sale.stream()
                .map(this::mapSalaToDTO)
                .collect(Collectors.toList());
    }

    private Sala mapSalaDTOToEntity(SalaDTO dto)
    {
        if (dto == null) return null;
        Sala sala = new Sala();
        sala.setNome(dto.getNome());
        sala.setCapienza(dto.getCapienza());
        sala.setUsufruibile(dto.isUsufruibile());
        return sala;
    }

    private void updateSalaFromDTO(Sala existingSala, SalaDTO dto)
    {
        if (existingSala == null || dto == null) return;
        if (dto.getNome() != null) existingSala.setNome(dto.getNome());
        if (dto.getCapienza() > 0) existingSala.setCapienza(dto.getCapienza());
        existingSala.setUsufruibile(dto.isUsufruibile());
    }
}