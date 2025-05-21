package org.elis.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.elis.dto.ProdottoMenuDTO;
import org.elis.dto.CategoriaProdottoDTO;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.elis.model.*;
import org.elis.model.Ordine.StatoOrdine;
import org.elis.repository.*;
import org.elis.service.ProdottoMenuService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class ProdottoMenuServiceImpl implements ProdottoMenuService
{
    private final ProdottoMenuRepository menuRepository;
    private final UtenteRepository utenteRepository;
    private final OrdineRepository ordineRepository;
    private final DettaglioOrdineRepository dettaglioOrdineRepository;
    private final ModelMapper modelMapper;
    private final CategoriaProdottoRepository categoriaProdottoRepository;


    public ProdottoMenuServiceImpl(ProdottoMenuRepository menuRepository, UtenteRepository utenteRepository, OrdineRepository ordineRepository, DettaglioOrdineRepository dettaglioOrdineRepository, ModelMapper modelMapper, CategoriaProdottoRepository categoriaProdottoRepository) {
        this.menuRepository = menuRepository;
        this.utenteRepository = utenteRepository;
        this.ordineRepository = ordineRepository;
        this.dettaglioOrdineRepository = dettaglioOrdineRepository;
        this.modelMapper = modelMapper;
        this.categoriaProdottoRepository = categoriaProdottoRepository;
    }

    @Override
    public List<ProdottoMenuDTO> getAllMenus()
    {
        List<ProdottoMenu> menus = menuRepository.findAll();
        return menus.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProdottoMenuDTO getMenuById(Long id)
    {
        ProdottoMenu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Menu non trovato con id: " + id));
        return mapEntityToDTO(menu);
    }

    @Override
    @Transactional
    public ProdottoMenuDTO createMenu(ProdottoMenuDTO prodottoMenuDTO)
    {
        ProdottoMenu menu = mapDTOToEntity(prodottoMenuDTO);
        menu = menuRepository.save(menu);
        return mapEntityToDTO(menu);
    }

    @Override
    @Transactional
    public ProdottoMenuDTO updateMenu(Long id, ProdottoMenuDTO prodottoMenuDTO)
    {
        ProdottoMenu existingMenu = menuRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Menu non trovato con id: " + id));

        updateEntityFromDTO(existingMenu, prodottoMenuDTO);

        ProdottoMenu updatedMenu = menuRepository.save(existingMenu);
        return mapEntityToDTO(updatedMenu);
    }

    @Override
    @Transactional
    public void deleteMenu(Long id)
    {
        menuRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void prenotaProdottoMenu(Long idUtente, Long idMenu, int quantita)
    {
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new RisorsaNonTrovataException("Utente non trovato con id: " + idUtente));
        ProdottoMenu menu = menuRepository.findById(idMenu)
                .orElseThrow(() -> new RisorsaNonTrovataException("Menu non trovato con id: " + idMenu));

        Ordine ordine = ordineRepository.findOrdineAttivoByUtente(utente)
                .orElseGet(() ->
                {
                    Ordine nuovoOrdine = new Ordine();
                    nuovoOrdine.setUtente(utente);
                    nuovoOrdine.setDataOrdine(new java.util.Date());
                    nuovoOrdine.setStatoOrdine(StatoOrdine.CREATO);
                    return ordineRepository.save(nuovoOrdine);
                });
        DettaglioOrdine dettaglioOrdine = new DettaglioOrdine();
        dettaglioOrdine.setOrdine(ordine);
        dettaglioOrdine.setMenu(menu);
        dettaglioOrdine.setQuantita(quantita);
        dettaglioOrdine.setPrezzoUnitario(menu.getPrezzo());
        dettaglioOrdineRepository.save(dettaglioOrdine);

        ordine.setImportoTotale(ordine.getImportoTotale() + (menu.getPrezzo() * quantita));
        ordineRepository.save(ordine);
    }

    @Override
    public Optional<ProdottoMenuDTO> findByNomeProdottoIgnoreCase(String nomeProdotto) 
    {
        Optional<ProdottoMenu> prodottoMenu = menuRepository.findByNomeProdottoIgnoreCase(nomeProdotto);
        return prodottoMenu.map(this::mapEntityToDTO);
    }

    @Override
    public List<ProdottoMenuDTO> findByCategoriaIgnoreCase(String categoria) 
    {
        List<ProdottoMenu> prodotti = menuRepository.findByCategoriaIgnoreCase(categoria);
        return prodotti.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdottoMenuDTO> findByPrezzoBetween(double minPrice, double maxPrice)
    {
        List<ProdottoMenu> prodotti = menuRepository.findByPrezzoBetween(minPrice, maxPrice);
        return prodotti.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }


    private ProdottoMenuDTO mapEntityToDTO(ProdottoMenu prodottoMenu)
    {
        if (prodottoMenu == null) return null;
        ProdottoMenuDTO dto = modelMapper.map(prodottoMenu, ProdottoMenuDTO.class);
        if (prodottoMenu.getCategoria() != null) 
        {
             CategoriaProdotto categoriaEntity = categoriaProdottoRepository.findByNomeIgnoreCase(prodottoMenu.getCategoria())
                     .orElse(null);
             if (categoriaEntity != null) 
             {
                  CategoriaProdottoDTO categoriaDTO = modelMapper.map(categoriaEntity, CategoriaProdottoDTO.class);
                  dto.setCategoria(categoriaDTO);
             }
        }
        return dto;
    }

    private ProdottoMenu mapDTOToEntity(ProdottoMenuDTO prodottoMenuDTO) 
    {
        if (prodottoMenuDTO == null) return null;
        ProdottoMenu entity = modelMapper.map(prodottoMenuDTO, ProdottoMenu.class);
        if (prodottoMenuDTO.getCategoria() != null && prodottoMenuDTO.getCategoria().getNome() != null) 
        {
             entity.setCategoria(prodottoMenuDTO.getCategoria().getNome());
        }
        return entity;
    }

    private void updateEntityFromDTO(ProdottoMenu existingEntity, ProdottoMenuDTO prodottoMenuDTO) 
    {
         if (existingEntity == null || prodottoMenuDTO == null) return;
         modelMapper.map(prodottoMenuDTO, existingEntity);
         if (prodottoMenuDTO.getCategoria() != null && prodottoMenuDTO.getCategoria().getNome() != null)
         {
              existingEntity.setCategoria(prodottoMenuDTO.getCategoria().getNome());
         }
    }
}