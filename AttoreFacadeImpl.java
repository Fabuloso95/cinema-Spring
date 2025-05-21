package org.elis.facade.impl;

import org.elis.dto.AttoreDTO;
import org.elis.facade.AttoreFacade;
import org.elis.service.AttoreService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttoreFacadeImpl implements AttoreFacade 
{
    private final AttoreService attoreService;

    public AttoreFacadeImpl(AttoreService attoreService) 
    {
        this.attoreService = attoreService;
    }

    @Override
    public List<AttoreDTO> getAllAttori() 
    {
        return attoreService.getAllAttori();
    }

    @Override
    public AttoreDTO getAttoreById(Long id) 
    {
        return attoreService.getAttoreById(id);
    }

    @Override
    public AttoreDTO createAttore(AttoreDTO attoreDTO)
    {
        return attoreService.createAttore(attoreDTO);
    }

    @Override
    public AttoreDTO updateAttore(Long id, AttoreDTO attoreDTO) 
    {
        return attoreService.updateAttore(id, attoreDTO);
    }

    @Override
    public void deleteAttore(Long id) 
    {
        attoreService.deleteAttore(id);
    }

    @Override
    public List<AttoreDTO> getAttoriByNome(String nome) 
    {
        return attoreService.getAttoriByNome(nome);
    }

    @Override
    public List<AttoreDTO> getAttoriByCognome(String cognome) 
    {
        return attoreService.getAttoriByCognome(cognome);
    }
}