package org.elis.facade;

import org.elis.dto.OrdineDTO;
import java.util.List;

public interface OrdineFacade 
{
    List<OrdineDTO> getAllOrdini();
    OrdineDTO getOrdineById(Long id);
    OrdineDTO createOrdine(OrdineDTO ordineDTO);
    OrdineDTO updateOrdine(Long id, OrdineDTO ordineDTO);
    void deleteOrdine(Long id);
}