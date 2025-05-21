package org.elis.facade.impl;

import org.elis.dto.RegistaDTO;
import org.elis.facade.RegistaFacade;
import org.elis.service.RegistaService;
import org.springframework.stereotype.*;
import java.util.List;
import java.util.Optional;

@Service
public class RegistaFacadeImpl implements RegistaFacade 
{
    private final RegistaService registaService;

    public RegistaFacadeImpl(RegistaService registaService) 
    {
        this.registaService = registaService;
    }

    @Override
    public List<RegistaDTO> getAllRegisti()
    {
        return registaService.getAllRegisti();
    }

    @Override
    public RegistaDTO getRegistaById(Long id) 
    {
        return registaService.getRegistaById(id);
    }

    @Override
    public RegistaDTO createRegista(RegistaDTO registaDTO)
    {
        return registaService.createRegista(registaDTO);
    }

    @Override
    public RegistaDTO updateRegista(Long id, RegistaDTO registaDTO)
    {
        return registaService.updateRegista(id, registaDTO);
    }

    @Override
    public void deleteRegista(Long id) 
    {
        registaService.deleteRegista(id);
    }

    @Override
    public Optional<RegistaDTO> findByNomeAndCognome(String nome, String cognome) 
    {
        return registaService.findByNomeAndCognome(nome, cognome);
    }
}