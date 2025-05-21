package org.elis.facade.impl;

import org.elis.dto.ProdottoMenuDTO;
import org.elis.facade.ProdottoMenuFacade;
import org.elis.service.ProdottoMenuService;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoMenuFacadeImpl implements ProdottoMenuFacade 
{
    private final ProdottoMenuService menuService;

    public ProdottoMenuFacadeImpl(ProdottoMenuService menuService) 
    {
        this.menuService = menuService;
    }

    @Override
    public List<ProdottoMenuDTO> getAllMenus() 
    {
        return menuService.getAllMenus();
    }

    @Override
    public ProdottoMenuDTO getMenuById(Long id) 
    {
        return menuService.getMenuById(id);
    }

    @Override
    public ProdottoMenuDTO createMenu(ProdottoMenuDTO prodottoMenuDTO) 
    {
        return menuService.createMenu(prodottoMenuDTO);
    }

    @Override
    public ProdottoMenuDTO updateMenu(Long id, ProdottoMenuDTO prodottoMenuDTO) 
    {
        return menuService.updateMenu(id, prodottoMenuDTO);
    }

    @Override
    public void deleteMenu(Long id) 
    {
        menuService.deleteMenu(id);
    }

    @Override
    public void prenotaProdottoMenu(Long idUtente, Long idMenu, int quantita) 
    {
        menuService.prenotaProdottoMenu(idUtente, idMenu, quantita);
    }

    @Override
    public Optional<ProdottoMenuDTO> findByNomeProdottoIgnoreCase(String nomeProdotto) 
    {
        return menuService.findByNomeProdottoIgnoreCase(nomeProdotto);
    }

    @Override
    public List<ProdottoMenuDTO> findByCategoriaIgnoreCase(String categoria) 
    {
        return menuService.findByCategoriaIgnoreCase(categoria);
    }

    @Override
    public List<ProdottoMenuDTO> findByPrezzoBetween(double minPrice, double maxPrice) 
    {
        return menuService.findByPrezzoBetween(minPrice, maxPrice);
    }
}