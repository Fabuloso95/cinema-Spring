package org.elis.facade.impl;

import org.elis.dto.BigliettoDTO;
import org.elis.facade.BigliettoFacade;
import org.elis.service.BigliettoService;
import org.springframework.stereotype.*;
import java.util.List;

@Service
public class BigliettoFacadeImpl implements BigliettoFacade 
{
    private final BigliettoService bigliettoService;

    public BigliettoFacadeImpl(BigliettoService bigliettoService) 
    {
        this.bigliettoService = bigliettoService;
    }

    @Override
    public List<BigliettoDTO> getAllBiglietti() 
    {
        return bigliettoService.getAllBiglietti();
    }

    @Override
    public BigliettoDTO getBigliettoById(Long id) 
    {
        return bigliettoService.getBigliettoById(id);
    }

    @Override
    public BigliettoDTO createBiglietto(BigliettoDTO bigliettoDTO) 
    {
        return bigliettoService.createBiglietto(bigliettoDTO);
    }

    @Override
    public BigliettoDTO updateBiglietto(Long id, BigliettoDTO bigliettoDTO) 
    {
        return bigliettoService.updateBiglietto(id, bigliettoDTO);
    }

    @Override
    public void deleteBiglietto(Long id) 
    {
        bigliettoService.deleteBiglietto(id);
    }
}