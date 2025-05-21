package org.elis.facade.impl;

import org.elis.dto.*;
import org.elis.facade.SpettacoloFacade;
import org.elis.service.SpettacoloService;
import org.springframework.stereotype.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SpettacoloFacadeImpl implements SpettacoloFacade
{
    private final SpettacoloService spettacoloService;

    public SpettacoloFacadeImpl(SpettacoloService spettacoloService) 
    {
        this.spettacoloService = spettacoloService;
    }

    @Override
    public List<SpettacoloDTO> getAllSpettacoli() 
    {
        return spettacoloService.getAllSpettacoli();
    }

    @Override
    public SpettacoloDTO getSpettacoloById(Long id) 
    {
        return spettacoloService.getSpettacoloById(id);
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
    public DettaglioSpettacoloDTO getDettaglioSpettacolo(Long id)
    {
        return spettacoloService.getDettaglioSpettacolo(id);
    }

    @Override
    public List<SpettacoloDTO> findByFilmId(Long filmId) 
    {
        return spettacoloService.findByFilmId(filmId);
    }

    @Override
    public List<SpettacoloDTO> findBySalaId(Long salaId) 
    {
        return spettacoloService.findBySalaId(salaId);
    }

    @Override
    public List<SpettacoloDTO> findByDataOraBetween(LocalDateTime start, LocalDateTime end) 
    {
        return spettacoloService.findByDataOraBetween(start, end);
    }
}