package org.elis.service;

import java.util.List;

import org.elis.dto.BigliettoDTO;

public interface BigliettoService 
{
    List<BigliettoDTO> getAllBiglietti();
    BigliettoDTO getBigliettoById(Long id);
    BigliettoDTO createBiglietto(BigliettoDTO bigliettoDTO);
    BigliettoDTO updateBiglietto(Long id, BigliettoDTO bigliettoDTO);
    void deleteBiglietto(Long id);
}