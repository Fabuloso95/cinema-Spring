package org.elis.facade.impl;

import org.elis.dto.ImpiegatoDTO;
import org.elis.dto.FilmDTO;
import org.elis.dto.SpettacoloDTO;
import org.elis.dto.ProdottoMenuDTO;
import org.elis.dto.SpettacoloCreationDTO;
import org.elis.dto.SpettacoloUpdateDTO;
import org.elis.facade.ImpiegatoFacade;
import org.elis.service.ImpiegatoService;
import org.springframework.stereotype.*;
import java.util.List;
import java.util.Optional;

@Service
public class ImpiegatoFacadeImpl implements ImpiegatoFacade 
{
    private final ImpiegatoService impiegatoService;

    public ImpiegatoFacadeImpl(ImpiegatoService impiegatoService) 
    {
        this.impiegatoService = impiegatoService;
    }

    @Override
    public List<ImpiegatoDTO> getAllImpiegati() 
    {
        return impiegatoService.getAllImpiegati();
    }

    @Override
    public ImpiegatoDTO getImpiegatoById(Long id) 
    {
        return impiegatoService.getImpiegatoById(id);
    }

    @Override
    public ImpiegatoDTO createImpiegato(ImpiegatoDTO impiegatoDTO) 
    {
        return impiegatoService.createImpiegato(impiegatoDTO);
    }

    @Override
    public ImpiegatoDTO updateImpiegato(Long id, ImpiegatoDTO impiegatoDTO) 
    {
        return impiegatoService.updateImpiegato(id, impiegatoDTO);
    }

    @Override
    public void deleteImpiegato(Long id)
    {
        impiegatoService.deleteImpiegato(id);
    }

    @Override
    public Optional<ImpiegatoDTO> findByUsername(String username) 
    {
        return impiegatoService.findByUsername(username);
    }

    @Override
    public FilmDTO createFilm(FilmDTO filmDTO) 
    {
        return impiegatoService.createFilm(filmDTO);
    }

    @Override
    public FilmDTO updateFilm(Long id, FilmDTO filmDTO) 
    {
        return impiegatoService.updateFilm(id, filmDTO);
    }

    @Override
    public void deleteFilm(Long id) 
    {
        impiegatoService.deleteFilm(id);
    }

    @Override
    public SpettacoloDTO createSpettacolo(SpettacoloCreationDTO spettacoloDTO) 
    {
        return impiegatoService.createSpettacolo(spettacoloDTO);
    }

    @Override
    public SpettacoloDTO updateSpettacolo(Long id, SpettacoloUpdateDTO spettacoloDTO) 
    {
        return impiegatoService.updateSpettacolo(id, spettacoloDTO);
    }

    @Override
    public void deleteSpettacolo(Long id) 
    {
        impiegatoService.deleteSpettacolo(id);
    }

    @Override
    public SpettacoloDTO updateSpettacoloPrezzoBase(Long idSpettacolo, double nuovoPrezzoBase)
    {
        return impiegatoService.updateSpettacoloPrezzoBase(idSpettacolo, nuovoPrezzoBase);
    }

    @Override
    public ProdottoMenuDTO createProdottoMenu(ProdottoMenuDTO prodottoMenuDTO) 
    {
        return impiegatoService.createProdottoMenu(prodottoMenuDTO);
    }

    @Override
    public ProdottoMenuDTO updateProdottoMenu(Long id, ProdottoMenuDTO prodottoMenuDTO)
    {
        return impiegatoService.updateProdottoMenu(id, prodottoMenuDTO);
    }

    @Override
    public void deleteProdottoMenu(Long id) 
    {
        impiegatoService.deleteProdottoMenu(id);
    }
}