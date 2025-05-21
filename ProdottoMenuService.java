package org.elis.service;

import org.elis.dto.ProdottoMenuDTO;
import java.util.List;
import java.util.Optional;

public interface ProdottoMenuService 
{
    List<ProdottoMenuDTO> getAllMenus();
    ProdottoMenuDTO getMenuById(Long id);
    ProdottoMenuDTO createMenu(ProdottoMenuDTO prodottoMenuDTO);
    ProdottoMenuDTO updateMenu(Long id, ProdottoMenuDTO prodottoMenuDTO);
    void deleteMenu(Long id);
    void prenotaProdottoMenu(Long idUtente, Long idMenu, int quantita);
    Optional<ProdottoMenuDTO> findByNomeProdottoIgnoreCase(String nomeProdotto);
    List<ProdottoMenuDTO> findByCategoriaIgnoreCase(String categoria);
    List<ProdottoMenuDTO> findByPrezzoBetween(double minPrice, double maxPrice);
}