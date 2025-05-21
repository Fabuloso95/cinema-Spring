package org.elis.facade.impl;

import org.elis.dto.SalaDTO;
import org.elis.facade.SalaFacade;
import org.elis.service.SalaService;
import org.springframework.stereotype.*;
import java.util.List;

@Service
public class SalaFacadeImpl implements SalaFacade 
{
    private final SalaService salaService;

    public SalaFacadeImpl(SalaService salaService) 
    {
        this.salaService = salaService;
    }

    @Override
    public List<SalaDTO> getAllSale() 
    {
        return salaService.getAllSale();
    }

    @Override
    public SalaDTO getSalaById(Long id)
    {
        return salaService.getSalaById(id);
    }

    @Override
    public SalaDTO createSala(SalaDTO salaDTO) 
    {
        return salaService.createSala(salaDTO);
    }

    @Override
    public SalaDTO updateSala(Long id, SalaDTO salaDTO) 
    {
        return salaService.updateSala(id, salaDTO);
    }

    @Override
    public void deleteSala(Long id) 
    {
        salaService.deleteSala(id);
    }

    @Override
    public SalaDTO getSalaByNome(String nome) 
    {
        return salaService.getSalaByNome(nome);
    }

    @Override
    public List<SalaDTO> getSaleByCapienza(int min) 
    {
        return salaService.getSaleByCapienza(min);
    }

    @Override
    public List<SalaDTO> getSaleUsufruibili() 
    {
        return salaService.getSaleUsufruibili();
    }
}