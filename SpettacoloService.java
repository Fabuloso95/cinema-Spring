package org.elis.service;

import java.time.LocalDateTime;
import java.util.List;

import org.elis.dto.DettaglioSpettacoloDTO;
import org.elis.dto.SpettacoloCreationDTO;
import org.elis.dto.SpettacoloDTO;
import org.elis.dto.SpettacoloUpdateDTO;

public interface SpettacoloService
{
    List<SpettacoloDTO> getAllSpettacoli();
    SpettacoloDTO getSpettacoloById(Long id);
    SpettacoloDTO createSpettacolo(SpettacoloCreationDTO  spettacoloDTO);
    SpettacoloDTO updateSpettacolo(Long id, SpettacoloUpdateDTO spettacoloDTO);
    void deleteSpettacolo(Long id);
    DettaglioSpettacoloDTO getDettaglioSpettacolo(Long id);
    List<SpettacoloDTO> findByFilmId(Long filmId);
    List<SpettacoloDTO> findBySalaId(Long salaId);
    List<SpettacoloDTO> findByDataOraBetween(LocalDateTime start, LocalDateTime end);
}