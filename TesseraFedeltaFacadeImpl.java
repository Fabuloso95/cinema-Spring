package org.elis.facade.impl;

import org.elis.dto.TesseraFedeltaDTO;
import org.elis.facade.TesseraFedeltaFacade;
import org.elis.service.TesseraFedeltaService;
import org.springframework.stereotype.*;
import java.util.List;

@Service
public class TesseraFedeltaFacadeImpl implements TesseraFedeltaFacade 
{
    private final TesseraFedeltaService tesseraFedeltaService;

    public TesseraFedeltaFacadeImpl(TesseraFedeltaService tesseraFedeltaService)
    {
        this.tesseraFedeltaService = tesseraFedeltaService;
    }

    @Override
    public List<TesseraFedeltaDTO> getAllTessereFedelta() 
    {
        return tesseraFedeltaService.getAllTessereFedelta();
    }

    @Override
    public TesseraFedeltaDTO getTesseraFedeltaById(Long id) 
    {
        return tesseraFedeltaService.getTesseraFedeltaById(id);
    }

    @Override
    public TesseraFedeltaDTO createTesseraFedelta(TesseraFedeltaDTO tesseraFedeltaDTO) 
    {
        return tesseraFedeltaService.createTesseraFedelta(tesseraFedeltaDTO);
    }

    @Override
    public TesseraFedeltaDTO updateTesseraFedelta(Long id, TesseraFedeltaDTO tesseraFedeltaDTO) 
    {
        return tesseraFedeltaService.updateTesseraFedelta(id, tesseraFedeltaDTO);
    }

    @Override
    public void deleteTesseraFedelta(Long id)
    {
        tesseraFedeltaService.deleteTesseraFedelta(id);
    }
}