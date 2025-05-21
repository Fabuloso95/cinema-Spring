package org.elis.facade.impl;

import org.elis.dto.OrdineDTO;
import org.elis.facade.OrdineFacade;
import org.elis.service.OrdineService;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class OrdineFacadeImpl implements OrdineFacade
{
    private final OrdineService ordineService;

    public OrdineFacadeImpl(OrdineService ordineService) 
    {
        this.ordineService = ordineService;
    }

    @Override
    public List<OrdineDTO> getAllOrdini() 
    {
        return ordineService.getAllOrdini();
    }

    @Override
    public OrdineDTO getOrdineById(Long id) 
    {
        return ordineService.getOrdineById(id);
    }

    @Override
    public OrdineDTO createOrdine(OrdineDTO ordineDTO) 
    {
        return ordineService.createOrdine(ordineDTO);
    }

    @Override
    public OrdineDTO updateOrdine(Long id, OrdineDTO ordineDTO) 
    {
        return ordineService.updateOrdine(id, ordineDTO);
    }

    @Override
    public void deleteOrdine(Long id)
    {
        ordineService.deleteOrdine(id);
    }
}