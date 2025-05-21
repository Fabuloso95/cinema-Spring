package org.elis.facade;

import org.elis.dto.BigliettoDTO;
import java.util.List;

public interface BigliettoFacade 
{
    List<BigliettoDTO> getAllBiglietti();
    BigliettoDTO getBigliettoById(Long id);
    BigliettoDTO createBiglietto(BigliettoDTO bigliettoDTO);
    BigliettoDTO updateBiglietto(Long id, BigliettoDTO bigliettoDTO);
    void deleteBiglietto(Long id);
}