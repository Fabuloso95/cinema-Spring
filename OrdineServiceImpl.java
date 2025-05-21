package org.elis.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import org.elis.dto.*;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.elis.model.*;
import org.elis.model.Ordine.StatoOrdine;
import org.elis.repository.*;
import org.elis.service.OrdineService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdineServiceImpl implements OrdineService
{
    private final OrdineRepository ordineRepository;
    private final UtenteRepository utenteRepository;
    private final ProdottoMenuRepository prodottoMenuRepository;
    private final ModelMapper modelMapper;

    public OrdineServiceImpl(OrdineRepository ordineRepository, UtenteRepository utenteRepository, ProdottoMenuRepository prodottoMenuRepository, ModelMapper modelMapper)
    {
        this.ordineRepository = ordineRepository;
        this.utenteRepository = utenteRepository;
        this.prodottoMenuRepository = prodottoMenuRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrdineDTO> getAllOrdini()
    {
        List<Ordine> ordini = ordineRepository.findAll();
        return ordini.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrdineDTO getOrdineById(Long id)
    {
        Ordine ordine = ordineRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Ordine non trovato con id: " + id));
        return mapEntityToDTO(ordine);
    }

    @Override
    @Transactional
    public OrdineDTO createOrdine(OrdineDTO ordineDTO)
    {
        Ordine ordine = new Ordine();

        if (ordineDTO.getUtenteId() == null) 
        {
            throw new IllegalArgumentException("ID Utente non fornito nel DTO di creazione ordine");
        }
        Utente utente = utenteRepository.findById(ordineDTO.getUtenteId())
                .orElseThrow(() -> new RisorsaNonTrovataException("Utente non trovato con ID: " + ordineDTO.getUtenteId()));
        ordine.setUtente(utente);

        ordine.setDataOrdine(ordineDTO.getDataOrdine() != null ? ordineDTO.getDataOrdine() : new Date());
        ordine.setStatoOrdine(ordineDTO.getStatoOrdine() != null ? StatoOrdine.valueOf(ordineDTO.getStatoOrdine()) : StatoOrdine.CREATO);

        if (ordineDTO.getDettagliOrdine() != null && !ordineDTO.getDettagliOrdine().isEmpty()) 
        {
            ordine.setDettagliOrdine(ordineDTO.getDettagliOrdine().stream()
                    .map(dettaglioDTO -> 
                    {
                        DettaglioOrdine dettaglio = modelMapper.map(dettaglioDTO, DettaglioOrdine.class);
                        dettaglio.setOrdine(ordine);

                        if (dettaglioDTO.getMenuId() == null)
                        {
                            throw new IllegalArgumentException("ID ProdottoMenu non fornito per un dettaglio ordine");
                        }
                        ProdottoMenu menu = prodottoMenuRepository.findById(dettaglioDTO.getMenuId())
                                .orElseThrow(() -> new RisorsaNonTrovataException("ProdottoMenu non trovato con ID: " + dettaglioDTO.getMenuId()));
                        dettaglio.setMenu(menu);

                        if (dettaglio.getPrezzoUnitario() <= 0) 
                        {
                             dettaglio.setPrezzoUnitario(menu.getPrezzo());
                        }

                        return dettaglio;
                    })
                    .collect(Collectors.toList()));

            double importoTotale = ordine.getDettagliOrdine().stream()
                    .mapToDouble(d -> d.getQuantita() * d.getPrezzoUnitario())
                    .sum();
            ordine.setImportoTotale(importoTotale);

        } 
        else 
        {
             ordine.setDettagliOrdine(new java.util.ArrayList<>());
             ordine.setImportoTotale(0.0);
        }


        Ordine nuovoOrdine = ordineRepository.save(ordine);

        return mapEntityToDTO(nuovoOrdine);
    }

    @Override
    @Transactional
    public OrdineDTO updateOrdine(Long id, OrdineDTO ordineDTO)
    {
        Ordine existingOrdine = ordineRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Ordine non trovato con id: " + id));

        if (ordineDTO.getDataOrdine() != null) existingOrdine.setDataOrdine(ordineDTO.getDataOrdine());
        if (ordineDTO.getStatoOrdine() != null) existingOrdine.setStatoOrdine(StatoOrdine.valueOf(ordineDTO.getStatoOrdine()));

        List<DettaglioOrdine> existingDettagli = existingOrdine.getDettagliOrdine();
        List<DettaglioOrdineDTO> updatedDettagliDTO = ordineDTO.getDettagliOrdine();

        Map<Long, DettaglioOrdine> existingDettagliMap = existingDettagli.stream()
                .filter(d -> d.getId() != null)
                .collect(Collectors.toMap(DettaglioOrdine::getId, d -> d));

        List<DettaglioOrdine> updatedDettagliListForEntity = new java.util.ArrayList<>();

        if (updatedDettagliDTO != null) 
        {
            for (DettaglioOrdineDTO dettaglioDTO : updatedDettagliDTO) 
            {
                if (dettaglioDTO.getId() != null && existingDettagliMap.containsKey(dettaglioDTO.getId()))
                {
                    DettaglioOrdine dettaglioToUpdate = existingDettagliMap.get(dettaglioDTO.getId());
                    dettaglioToUpdate.setQuantita(dettaglioDTO.getQuantita());
                    updatedDettagliListForEntity.add(dettaglioToUpdate);
                    existingDettagliMap.remove(dettaglioDTO.getId());
                } 
                else 
                {
                    DettaglioOrdine nuovoDettaglio = modelMapper.map(dettaglioDTO, DettaglioOrdine.class);
                    nuovoDettaglio.setId(null);
                    nuovoDettaglio.setOrdine(existingOrdine);

                    if (dettaglioDTO.getMenuId() == null)
                    {
                        throw new IllegalArgumentException("ID ProdottoMenu non fornito per un nuovo dettaglio ordine");
                    }
                    ProdottoMenu menu = prodottoMenuRepository.findById(dettaglioDTO.getMenuId())
                            .orElseThrow(() -> new RisorsaNonTrovataException("ProdottoMenu non trovato con ID: " + dettaglioDTO.getMenuId() + " per nuovo dettaglio"));
                    nuovoDettaglio.setMenu(menu);

                    if (nuovoDettaglio.getPrezzoUnitario() <= 0) 
                    {
                         nuovoDettaglio.setPrezzoUnitario(menu.getPrezzo());
                    }

                    updatedDettagliListForEntity.add(nuovoDettaglio);
                }
            }
        }

        existingDettagli.clear();
        existingDettagli.addAll(updatedDettagliListForEntity);

        double importoTotale = existingOrdine.getDettagliOrdine().stream()
                .mapToDouble(d -> d.getQuantita() * d.getPrezzoUnitario())
                .sum();
        existingOrdine.setImportoTotale(importoTotale);


        Ordine updatedOrdine = ordineRepository.save(existingOrdine);

        return mapEntityToDTO(updatedOrdine);
    }

    @Override
    @Transactional
    public void deleteOrdine(Long id)
    {
        if (!ordineRepository.existsById(id))
        {
            throw new RisorsaNonTrovataException("Ordine non trovato con id: " + id);
        }
        ordineRepository.deleteById(id);
    }

    private OrdineDTO mapEntityToDTO(Ordine ordine)
    {
        if (ordine == null) return null;
        OrdineDTO dto = modelMapper.map(ordine, OrdineDTO.class);

        if (ordine.getUtente() != null)
        {
            dto.setUtenteId(ordine.getUtente().getId());
        }

        if (ordine.getDettagliOrdine() != null)
        {
            dto.setDettagliOrdine(ordine.getDettagliOrdine().stream()
                    .map(this::mapDettaglioOrdineEntityToDTO)
                    .collect(Collectors.toList()));
        } 
        else 
        {
             dto.setDettagliOrdine(new java.util.ArrayList<>());
        }

        if (ordine.getStatoOrdine() != null) 
        {
            dto.setStatoOrdine(ordine.getStatoOrdine().name());
        }

        return dto;
    }

    private DettaglioOrdineDTO mapDettaglioOrdineEntityToDTO(DettaglioOrdine dettaglioOrdine)
    {
        if (dettaglioOrdine == null) return null;
        DettaglioOrdineDTO dto = modelMapper.map(dettaglioOrdine, DettaglioOrdineDTO.class);
        if (dettaglioOrdine.getMenu() != null) 
        {
            dto.setMenuId(dettaglioOrdine.getMenu().getId());
        }
        return dto;
    }

     @SuppressWarnings("unused")
	private Ordine mapDTOToEntity(OrdineDTO ordineDTO)
     {
         if (ordineDTO == null) return null;
         Ordine ordine = modelMapper.map(ordineDTO, Ordine.class);
         ordine.setUtente(null);
         ordine.setDettagliOrdine(new java.util.ArrayList<>());
         return ordine;
     }

     @SuppressWarnings("unused")
	private void updateEntityFromDTO(Ordine existingOrdine, OrdineDTO ordineDTO)
     {
         if (existingOrdine == null || ordineDTO == null) return;
         if (ordineDTO.getDataOrdine() != null) existingOrdine.setDataOrdine(ordineDTO.getDataOrdine());
         if (ordineDTO.getStatoOrdine() != null) existingOrdine.setStatoOrdine(StatoOrdine.valueOf(ordineDTO.getStatoOrdine()));
     }
}